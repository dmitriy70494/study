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

    public void init() {

    }

    /**
     * не существует ли еще такой пользователь
     *
     * @param user
     */
    public String add(User user) {
        return this.storage.add(user);
    }

    /**
     * Например, при обновлении полей нужно проверить. что такой пользователь существует.
     *
     * @param user
     */
    public String update(String id, User user) {
        String result = "update complite";
        try {
            if (!this.storage.update(id, user)) {
                result = "not update";
            }
        } catch (IllegalArgumentException ie) {
            result = ie.getMessage();
        }
        return result;
    }

    /**
     * проверку на существование
     *
     * @param id
     */
    public String delete(String id) {
        String result = "не удалось удалить пользователя";
        if (this.storage.delete(id)) {
            result = "пользователь успешно удален";
        }
        return result;
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
}
