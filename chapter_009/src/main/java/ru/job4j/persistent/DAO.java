package ru.job4j.persistent;

import java.util.List;

public interface DAO<T> {

    boolean add(T obj);

    List<T> findAll();

    T findById(String id);

    boolean update(T obj);

    boolean delete(String id);
}
