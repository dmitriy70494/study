package ru.job4j.tracker;

/**
 * Интерфейс для действий пользователей
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public interface UserAction {

    /**
     * возвращает номер пункта меню
     * @return int номер пункта меню в массиве
     */
    int key();

    /**
     * Запускает на исполнение выбранный пункт меню
     * @param input   ввод вывод в консоль
     * @param tracker хранилище всех заявок
     */
    void execute(Input input, Tracker tracker);

    /**
     * отображает пунк меню для отображения пользователю
     * @return String отображает пунк меню для отображения пользователю
     */
    String info();
}
