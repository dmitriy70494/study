package ru.job4j.tracker;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Содержит методы, которыми можно работать с данными. Все данные хранятся в списке объектов Item
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 24.04.2018
 */
public class Tracker implements AutoCloseable {

    /**
     * Логер, записывает все данные произошедшие в методах в лог файлы
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(Tracker.class);


    /**
     * позволяет генерировать случайное число
     */
    private static final Random RN = new Random();

    /**
     * Настройки подключение базы данных и скрипты должны находиться в отдельном файле и считываться при старте.
     * Если количество запросов вырастет, то нужно учесть увеличение initialCapasity сейчас она соотетствует количеству запросов + 5 чтобы заполненность не была менее 75%
     */
    HashMap<String, String> scripts = new HashMap<String, String>(18);

    /**
     * Соединение с базой данных
     */
    private Connection connection;

    private static Tracker tracker;

    public Tracker() {
    }

    /**
     * Создает трекер, и заносит набор скриптов, инициализирует соединение
     *
     * @param config
     * @return
     */
    public Tracker init(final String config) {
        tracker = new Tracker();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(config)))) {
            String line = reader.readLine();
            while (line != null) {
                tracker.scripts.put(line, reader.readLine());
                line = reader.readLine();
            }
            String urlRoot = tracker.scripts.get("url_root");
            String urlDatabase = tracker.scripts.get("url_database");
            String username = tracker.scripts.get("username");
            String password = tracker.scripts.get("password");
            Connection connectionRoot = DriverManager.getConnection(urlRoot, username, password);
            Statement statement = connectionRoot.createStatement();
            if (!statement.executeQuery(tracker.scripts.get("check_database")).next()) {
                statement.execute(tracker.scripts.get("create_database"));
            }
            connectionRoot.close();
            tracker.connection = DriverManager.getConnection(urlDatabase, username, password);
            statement = tracker.connection.createStatement();
            int index = 0;
            String create;
            while (true) {
                create = tracker.scripts.get("create_table" + index);
                if (create == null) {
                    break;
                } else if (!statement.executeQuery(tracker.scripts.get("check_table" + index++)).next()) {
                    statement.execute(create);
                }
            }
            statement.close();
        } catch (IOException | SQLException ie) {
            ie.printStackTrace();
            LOGGER.error(ie.getMessage(), ie);
            try {
                tracker.connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
                LOGGER.error(se.getMessage(), se);
            }
        }
        return tracker;
    }

    /**
     * Данный метод генерирует уникальный ID, Добавляет его в экземпляр Item и добавляет его в
     * таблицу для хранения данных программы. Всего можно добавить 99 элементов (связано с работой метода Delete)
     *
     * @param item Новый элемент с данными.
     * @return ru возвращает добавленный элемент с присвоенным ID
     */
    public Item add(Item item) {
        ResultSet key = null;
        try (PreparedStatement statement = connection.prepareStatement(scripts.get("insert_user"), Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDesc());
            statement.setTimestamp(3, new Timestamp(item.getCreated()));
            statement.executeUpdate();
            key = statement.getGeneratedKeys();
            if (key.next()) {
                item.setId(key.getString(1));
                key.close();
                return item;
            }
        } catch (SQLException se) {
            try {
                key.close();
            } catch (SQLException sqe) {
                LOGGER.error(se.getMessage(), sqe);
            }
            LOGGER.error(se.getMessage(), se);

        }
        throw new IllegalStateException("Could not create new user");
    }


    /**
     * Изменяет ячейку массива. Поиск ячейки по Id
     *
     * @param id   id добавленного элемента.
     * @param item сам элемент с изменениями, может быть новым, так как метод поменяет id, который указан в первом параметре
     */
    public void replace(String id, Item item) {
        try (PreparedStatement statment = connection.prepareStatement(scripts.get("update_user"))) {
            statment.setString(1, item.getName());
            statment.setString(2, item.getDesc());
            statment.setInt(3, Integer.valueOf(id));
            statment.executeUpdate();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage(), se);
        }
    }

    /**
     * Удаляет элемент с заданным id и сдвигает все элементы влево. Если нет такого id, то ничего не делает
     *
     * @param id id элемента, который нужно удвалить.
     */
    public void delete(String id) {
        try (PreparedStatement statement = connection.prepareStatement(scripts.get("delete_user"))) {
            statement.setInt(1, Integer.valueOf(id));
            statement.executeUpdate();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage(), se);
        }
    }

    /**
     * Возвращает список со всеми элементами, без пустых ячеек
     *
     * @return copyItems список элементов, null - если список пуст
     */
    public List<Item> findAll() {
        try (
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(scripts.get("select_users"))) {
            List<Item> items = new ArrayList<>(100);
            while (result.next()) {
                items.add(new Item(String.valueOf(result.getInt("id")), result.getString("name"), result.getString("description")));
            }
            return items;
        } catch (SQLException se) {
            LOGGER.error(se.getMessage(), se);
        }
        throw new IllegalStateException("findAll crashed");
    }

    /**
     * Ищет все совпадения по имени, если находит возвращает список элементов
     *
     * @param key поле name Item.
     * @return find список найденных, если нет найденных то список пустой
     */
    public List<Item> findByName(String key) {
        try (PreparedStatement statment = connection.prepareStatement(scripts.get("select_name"))) {
            List<Item> find = new ArrayList<Item>();
            statment.setString(1, key);
            try (ResultSet result = statment.executeQuery()) {
                while (result.next()) {
                    find.add(new Item(String.valueOf(result.getInt("id")), result.getString("name"), result.getString("description")));
                }
            }
            return find;
        } catch (SQLException se) {
            LOGGER.error(se.getMessage(), se);
        }
        throw new IllegalArgumentException("Could not find by name");
    }

    /**
     * Ищет элемент по id и возвращает его
     *
     * @param id id элемента, который нужно найти.
     * @return ru если нет такого элемента, возвратит null
     */
    public Item findById(String id) {
        try (PreparedStatement statment = connection.prepareStatement(scripts.get("select_id"))) {
            Item find = null;
            statment.setInt(1, Integer.valueOf(id));
            try (ResultSet result = statment.executeQuery()) {
                if (result.next()) {
                    find = new Item(String.valueOf(result.getInt("id")), result.getString("name"), result.getString("description"));
                }
            }
            return find;
        } catch (SQLException se) {
            LOGGER.error(se.getMessage(), se);
        }
        throw new IllegalArgumentException("Could not find by name");
    }

    /**
     * дает таблицу
     *
     * @return items таблица с данными
     */
    public List<Item> getItems() {
        return this.findAll();
    }

    /**
     * Метод должен закрывать используемые ресурсы
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
