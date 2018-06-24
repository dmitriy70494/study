package ru.job4j.servlets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Далее создайте класс ValidateService - это слой Logic. В нем нужно добавить методы
 * add, update, delete, findAll, findById
 * <p>
 * Каждый метод должен производить валидацию данных. Например, при обновлении полей нужно проверить. что такой пользователь существует.
 * <p>
 * <p>
 * Класс ValidateService сделать синглетоном. Использовать Eager initiazitation.
 */
public class ValidateService {

    private final static ValidateService instance = new ValidateService();

    private final Store storage = DBStore.getInstance();

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return instance;
    }

    /**
     * не существует ли еще такой пользователь
     *
     * @param user
     */
    public boolean add(User user) {
        return this.storage.add(user);
    }

    /**
     * Например, при обновлении полей нужно проверить. что такой пользователь существует.
     *
     * @param user
     */
    public boolean update(String id, User user) {
        return this.storage.update(id, user);
    }

    /**
     * проверку на существование
     *
     * @param id
     */
    public boolean delete(String id) {
        return this.storage.delete(id);
    }

    /**
     *
     */
    public ArrayList<User> findAll() {
        return storage.findAll();
    }

    /**
     * @param id
     */
    public User findById(String id) {
        return storage.findById(id);
    }

    public User findCredential(String login, String password) {
        return this.storage.findCredential(login, password);
    }
}
