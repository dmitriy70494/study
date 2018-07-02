package ru.job4j.persistent;

import ru.job4j.logic.User;

import java.util.List;

public interface UserDAO<T extends User> extends DAO<T> {

    List query(AccountSpecification specification);
}
