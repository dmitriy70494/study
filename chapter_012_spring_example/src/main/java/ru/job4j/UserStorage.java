package ru.job4j;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserStorage {

    private static final Logger log = LoggerFactory.getLogger(UserStorage.class);

    private final Storage storage;

    @Autowired
    public UserStorage(final JdbcStorage storage) {
        this.storage = storage;
    }

    public void add(User user) {
        this.storage.add(user);
    }
}
