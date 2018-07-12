package ru.job4j.persist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.job4j.Motor;

@Component
public interface MotorDataRepository  extends CrudRepository<Motor, Integer> {
}
