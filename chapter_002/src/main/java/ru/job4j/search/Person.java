package ru.job4j.search;

/**
 * класс Person. Содержит информацию о персоне, также геттеры, и конструктор
 * для инициализации объекта
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 01.05.2018
 */
public class Person {
    private String name;
    private String surname;
    private String phone;
    private String address;

    /**
     * Конструктор инициализирует параметры персоны
     *
     * @param name имя
     * @param surname Фамилия
     * @param phone телефон
     * @param address город
     */
    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
