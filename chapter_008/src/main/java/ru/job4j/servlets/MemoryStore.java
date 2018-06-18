package ru.job4j.servlets;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class MemoryStore implements Store {

    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>(1000);

    private final static Store instance = new MemoryStore();

    private int size = 0;

    private MemoryStore() {

    }

    public static Store getInstance() {
        return instance;
    }

    @Override
    public String add(User user) {
        String result = checkUser(user);
        if (result == null) {
            user.setId(++this.size);
            if (this.users.put(user.getId(), user) == null) {
                result = "the user has been added";
            } else {
                result = "the user not has been added";
            }
        }
        return result;
    }

    private String checkUser(User checked) {
        String result = null;
        for (User user : this.users.values()) {
            if (user.getLogin().equals(checked.getLogin())) {
                result = "a user with this login already exists";
            }
            if (user.getEmail().equals(checked.getEmail())) {
                result = "a user with this email already exists";
            }
        }
        return result;
    }

    @Override
    public boolean update(String id, User user) {
        int index = Integer.valueOf(id);
        boolean access = false;
        if (users.containsKey(index)) {
            access = this.users.put(index, user).getId() == index;
        }
        return access;
    }

    @Override
    public boolean delete(String id) {
        return this.users.remove(Integer.valueOf(id)) != null;
    }

    @Override
    public Collection<User> findAll() {
        return this.users.values();
    }

    @Override
    public User findById(String id) {
        return this.users.get(Integer.valueOf(id));
    }
}
