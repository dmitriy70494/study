package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.domain.Car;
import ru.job4j.domain.User;
import ru.job4j.repository.CarRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private CarRepository repository;

    @Autowired
    public CarServiceImpl (CarRepository store) {
        this.repository = store;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Car> findAll() {
        return this.repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findCarsByName(String name) {
        return this.repository.findCarsByName(name);
    }

    @Override
    @Transactional
    public void updateDone(Integer id) {
        this.repository.updateDone(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findCarsByUser(User user) {
        return this.repository.findCarsByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findCarsByCreateAfter(Timestamp lastDay) {
        return this.repository.findCarsByCreateAfter(lastDay);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findCarsByFotoNotNull() {
        return this.repository.findCarsByFotoNotNull();
    }
}
