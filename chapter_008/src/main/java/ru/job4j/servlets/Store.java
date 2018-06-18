package ru.job4j.servlets;


import java.util.Collection;

public interface Store {

    String add(User user);

    boolean update(String id, User user);

    boolean delete(String id);

    Collection<User> findAll();

    User findById(String id);
}
