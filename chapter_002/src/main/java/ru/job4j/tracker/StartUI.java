package ru.job4j.tracker;

import java.io.IOException;

/**
 * Запускает приложение
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class StartUI {

    private int[] ranges;

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     * Отображается меню, пользователь вводит цифру и запускает Меню, и действия пользователя
     * в цикле
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        ranges = menu.makeRange();
        do {
            menu.show();
                menu.select(this.input.ask("Введите пункт меню : ", ranges));
        } while (!"да".equals(this.input.ask("Выйти? (да)")));
    }

    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}
