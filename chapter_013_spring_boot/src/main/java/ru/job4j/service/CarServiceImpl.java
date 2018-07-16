package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Car;
import ru.job4j.domain.User;
import ru.job4j.repository.CarRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository repository;

    @Autowired
    public CarServiceImpl (CarRepository store) {
        this.repository = store;
    }

    @Override
    public Iterable<Car> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Car> findCarsByName(String name) {
        return this.repository.findCarsByName(name);
    }

    @Override
    public void updateDone(Integer id) {
        this.repository.updateDone(id);
    }

    @Override
    public List<Car> findCarsByUser(User user) {
        return this.repository.findCarsByUser(user);
    }

    @Override
    public List<Car> findCarsByCreateAfter(Timestamp lastDay) {
        return this.repository.findCarsByCreateAfter(lastDay);
    }

    @Override
    public List<Car> findCarsByFotoNotNull() {
        return this.repository.findCarsByFotoNotNull();
    }
}
