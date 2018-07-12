package ru.job4j.persist;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import ru.job4j.Car;
import ru.job4j.User;

import java.sql.Timestamp;
import java.util.List;

@Component
public interface CarDataRepository extends CrudRepository<Car, Integer> {

    List findCarsByName(String name);

    @Modifying
    @Query("UPDATE Car c SET c.done = false WHERE c.id = :id")
    int updateDone(@Param("id") int id);

    List findCarsByUser(User user);


    List findCarsByCreateAfter(Timestamp date);

    List findCarsByFotoNotNull();
}
