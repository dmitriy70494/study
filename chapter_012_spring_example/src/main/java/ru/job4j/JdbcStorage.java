package ru.job4j;

public class JdbcStorage implements Storage {
    @Override
    public void add(User user) {
        System.out.println("store to memory");
    }
}
