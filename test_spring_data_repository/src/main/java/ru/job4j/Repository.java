package ru.job4j;

import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<User, Integer> {
}
