package ru.job4j.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Car;
import ru.job4j.domain.User;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface CarService {

    Iterable<Car> findAll();

    List<Car> findCarsByName(String name);

    void updateDone(Integer id);

    List<Car> findCarsByUser(User user);

    List<Car> findCarsByCreateAfter(Timestamp lastDay);

    List<Car> findCarsByFotoNotNull();
}
