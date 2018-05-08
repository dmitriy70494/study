package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс содержит информацию о клиенте банка. ФИО и паспортные данные
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 07.05.2018
 */
public class User {

    /**
     * Фио пользователя
     */
    private final String name;

    /**
     * Паспортные данные пользователя
     */
    private final String passport;

    /**
     * Конструктор инициализирует данные
     *
     * @param name ФИО
     * @param passport папортные данные
     */
    public User(final String name, final String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return user != null ? this == user || this.passport.equals(user.passport) : false;
    }

    @Override
    public int hashCode() {
        return passport.hashCode();
    }

    @Override
    public String toString() {
        return name + " " + passport;
    }
}
