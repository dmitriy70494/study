package ru.job4j.user;

/**
 * Класс User. Хранит информацию о пользователе
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 03.05.2018
 */
public class User {

    /**
     * Уникальный номер пользователя
     */
    private int id;

    /**
     * Имя пользователя
     */
    private String name;

    /**
     * Город
     */
    private String city;

    /**
     * Конструктор для инициализации переменных
     *
     * @param id Уникальный номер пользователя
     * @param name Имя пользователя
     * @param city Город
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String city() {
        return this.city;
    }
}
