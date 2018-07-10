package ru.job4j;

import org.springframework.stereotype.Component;

@Component
public class MemoryStorage implements Storage {

    @Override
    public void add(User user) {
        System.out.println("add memory");
    }
}
