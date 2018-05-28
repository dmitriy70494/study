package ru.job4j.multithread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Objects;

/**
 * класс User. Создает объект пользователя
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 28.05.2018
 */
public class User {

    /**
     * Уникальный идентификатор
     */
    private final int id;

    /**
     * Сумма на счете
     */
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Добавляет или отнимает сумму со счета
     * @param amount
     * @return
     */
    protected boolean add(int amount) {
        int check = this.amount;
        this.amount += amount;
        return check + amount == this.amount;
    }

    protected int getId() {
        return this.id;
    }

    protected int getAmount() {
        return this.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}