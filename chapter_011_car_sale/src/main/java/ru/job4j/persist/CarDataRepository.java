package ru.job4j.persist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.job4j.Car;

public interface CarDataRepository extends CrudRepository<Car, Integer> {
}
