package ru.job4j.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Car;
import ru.job4j.domain.User;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {

    List<Car> findCarsByName(String name);

    @Modifying
    @Query("UPDATE Car c SET c.done = false WHERE c.id = :id")
    void updateDone(@Param("id") int id);

    List<Car> findCarsByUser(User user);


    List<Car> findCarsByCreateAfter(Timestamp date);

    List<Car> findCarsByFotoNotNull();

}
