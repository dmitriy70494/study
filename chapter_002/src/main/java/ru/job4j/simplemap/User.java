package ru.job4j.simplemap;

import java.util.Calendar;
import java.util.Objects;

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
    public boolean equals(Object object) {
        boolean access = !(object == null || this.getClass() != object.getClass() || this == object);
        User user = null;
        if (access) {
            user = (User) object;
        }
        return access && (this.name != null ? this.name.equals(user.name) : user.name == null) && (this.birthday != null ? this.birthday.equals(user.birthday) : user.birthday == null) && children == children;
    }

    @Override
    public int hashCode() {
        return 31 * (name == null ? 0 : name.hashCode() + (birthday == null ? 0 : birthday.hashCode())) + children;
    }
}
