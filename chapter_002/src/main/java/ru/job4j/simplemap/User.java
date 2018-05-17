package ru.job4j.simplemap;

import java.util.Calendar;

/**
 * класс User. Хранит 3 поля данных о пользователе.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 15.05.2018
 */
public class User {

    String name;

    int children;

    Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return 31 * (name == null ? 0 : name.hashCode() + (birthday == null ? 0 : birthday.hashCode())) + children;
    }
}
