package ru.job4j.magnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.bufferedfilereader.BufferedFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Config - объект содержащий настройки для подключения к базе.
 */
public class Config {

    private final static Logger LOGGER = LoggerFactory.getLogger(Config.class);

    /**
     * Хранит данные о возможных командах в данных и их индекс. Решил реализовать именно так, потому что вызывая сроки кода
     * в StoreSQL мы начинаем использовать много лишних постоянных команд, и это становится критично, когда нужно их все найти
     * выходом бы могло быть создание Именованных постоянных переменных, но их пришлось бы создавать очень много и много лишних,
     * и разных для разных объектов, это не позволило бы в частности использовать этот класс в других классах для решения не только этой задачи
     * Поэтому я решил реализовать это так, все команды перед использованием можно будет просмотреть и так как они содержат текст легко понять,
     * что они означают... Хотя понимаю что это возможно не самое лучшее решение.
     */
    private List<String> commands = new ArrayList<>();

    /**
     * все запросы, ключи к запросам команды
     */
    private Map<String, String> config = new HashMap<>(20);

    /**
     * Минимальный размер команд, без которых приложение не сможет подключиться к базе данных, создана на случай, когда
     * лист конфигурации существует, но пуст.
     */
    private final static int MIN_SIZE = 4;

    /**
     * Приватный конструктор, объект создает и передает на него ссылку метод initialConfig
     *
     */
    private Config() {
    }

    /**
     * Решил попробовать инициализировать объект не через конструктор, а через статический метод, как рекомендовал Блох,
     * возможно в данном случае это излишне, так как это больше подходит для классов которые нужно создавать по разному для
     * понимания для чего они создвются, как Executore например. Но в этом классе мне необходило было проинициализировать
     * объект, прочитав конфигурацию из файла и создавая объект через конструктор, пришлось бы вызывать метод инициализации
     * иначе бы объект неправильно работал. Наверняка не стоит писать много кода в статическом методе, почитать 
     * про это потом подробнее. Если посмотреть в DriverManager, то там все остальные вызовы приходятся на статические методы.
     * Хотя скорее всего там какой то более глобальный паттерн.
     *
     * @param file
     * @return
     * @throws IllegalArgumentException
     */
    public static <T> Config initialConfig(T file)  {
        return new Config().init(file);
    }
    
    private <T> Config init(T file) throws IllegalArgumentException {
        BufferedFileReader reader = BufferedFileReader.instanceWithLogging(file, "UTF-8", LOGGER);
        int count = 0;
        while (reader.hasNext()) {
            this.commands.add(reader.nextLine());
            this.config.put(commands.get(count++), reader.nextLine());
        }
        reader.close();
        if (config.size() < MIN_SIZE) {
            throw new IllegalArgumentException("Min size operation less necessary");
        }
        return this;
    }

    public String command(String requestCommand) {
        return this.config.get(requestCommand);
    }

    /**
     * передает массив команд, чтобы нельзя было испортить оригинальный список, хотя это возможно излишне
     *
     * @return
     */
    public String[] getCommands() {
        return this.commands.toArray(new String[commands.size()]);
    }
}
