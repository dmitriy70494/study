package ru.job4j.servlets;


import java.util.ArrayList;

public interface Store {

    String add(User user);

    boolean update(String id, User user);

    boolean delete(String id);

    ArrayList<User> findAll();

    User findById(String id);
}
