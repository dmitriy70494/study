package ru.job4j;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MemoryStorage implements Storage {

    HashMap<Integer, User> users = new HashMap<>();

    @Override
    public void add(User user) {
        this.users.put(user.getId(), user);
    }

    public User findById(Integer id) {
        return this.users.get(id);
    }
}
