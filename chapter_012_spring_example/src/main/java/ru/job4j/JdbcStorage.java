package ru.job4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;

@Component
public class JdbcStorage implements Storage {

    JdbcTemplate template;

    @Autowired
    public JdbcStorage(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void add(User user) {
        template.update("INSERT INTO users(login, password) VALUES (?,?)", user.getLogin(), user.getPassword());
    }
}
