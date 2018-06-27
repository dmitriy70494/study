package ru.job4j.servlets;


import java.util.ArrayList;
import java.util.List;

public interface Store {

    boolean add(User user);

    boolean update(String id, User user);

    boolean delete(String id);

    ArrayList<User> findAll();

    User findById(String id);

    User findCredential(String login, String password);

    List<Person> getAllPerson();

    boolean addPerson(Person person);

    boolean updatePerson(String id, Person person);
}
