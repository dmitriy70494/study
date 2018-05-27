package ru.job4j.multithread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * класс UserStorage. Создает потокобезопасное хранилище
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 28.05.2018
 */
@ThreadSafe
public class UserStorage {

    /**
     * Хранит всех пользователей по ключу
     */
    @GuardedBy("users")
    private final HashMap<Integer, User> users;

    /**
     * Конструктор инициализирует коллекцию с размером 100 ячеек
     */
    public UserStorage() {
        users = new HashMap<Integer, User>(100);
    }

    /**
     * Добавляет пользователя, при добавлении другие потоки не могут использовать список
     * @param user
     * @return
     */
    public boolean add(User user) {
        synchronized (users) {
            return users.putIfAbsent(user.getId(), user) == null;
        }
    }

    /**
     * вносит изменения в пользователя
     * @param user
     * @return
     */
    public boolean update(User user) {
        synchronized (users) {
            return users.replace(user.getId(), user) != null;
        }
    }

    /**
     * Удаляет пользователя из системы
     * @param user
     * @return
     */
    public boolean delete(User user) {
        synchronized (users) {
            return users.remove(user.getId(), user);
        }
    }

    /**
     * Перечисляет средства с 1 счета на другой если достаточно средств
     * @param fromId
     * @param toId
     * @param amount
     * @return
     */
    public boolean transfer(int fromId, int toId, int amount) {
        synchronized (users) {
            User fromUser = users.get(fromId);
            User toUser = users.get(toId);
            return fromUser != null && toUser != null && fromUser.getAmount() >= amount
                    && fromUser.add(-amount) && toUser.add(amount);
        }
    }

    public int size() {
        return users.size();
    }
}
