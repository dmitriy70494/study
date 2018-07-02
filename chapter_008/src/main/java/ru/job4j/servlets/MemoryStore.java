package ru.job4j.servlets;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class MemoryStore implements Store {

    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>(1000);

    private final static MemoryStore instance = new MemoryStore();

    private int size = 0;

    private MemoryStore() {

    }

    public static Store getInstance() {
        return instance;
    }

    @Override
    public boolean add(User user) {
        boolean access = checkUser(user);
        if (access) {
            user.setId(++this.size);
            access = this.users.put(user.getId(), user) != null;
        }
        return access;
    }

    private boolean checkUser(User checked) {
        boolean access = true;
        for (User user : this.users.values()) {
            if (user.getLogin().equals(checked.getLogin()) || user.getEmail().equals(checked.getEmail())) {
                access = false;
            }
        }
        return access;
    }

    @Override
    public boolean update(String id, User user) {
        int index = Integer.valueOf(id);
        boolean access = users.containsKey(index);
        if (access) {
        user.setId(index);
        this.users.put(index, user);
        }
        return access;
    }

    @Override
    public boolean delete(String id) {
        return this.users.remove(Integer.valueOf(id)) != null;
    }

    @Override
    public ArrayList<User> findAll() {
        return new ArrayList<>(this.users.values());
    }

    @Override
    public User findById(String id) {
        return this.users.get(Integer.valueOf(id));
    }

    @Override
    public User findCredential(String login, String password) {
        return null;
    }

    @Override
    public List<Person> getAllPerson() {
        return null;
    }

    @Override
    public boolean addPerson(Person person) {
        return false;
    }

    @Override
    public boolean updatePerson(String id, Person person) {
        return false;
    }
}
