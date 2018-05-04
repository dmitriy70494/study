package ru.job4j.sort;

/**
 * класс User. Хранит данные о пользователе
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 03.05.2018
 */
public class User implements Comparable<User> {

    /**
     * Имя пользователя
     */
    private String name;

    /**
     * Возраст пользователя
     */
    private int age;

    /**
     * Конструктор. Инициализирует имя и возраст пользователя
     * @param name имя
     * @param age возраст
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int compareTo(User user) {
        return Integer.valueOf(age).compareTo(user.age);
    }

    @Override
    public String toString() {
        return name + " " + age + " ";
    }
}
