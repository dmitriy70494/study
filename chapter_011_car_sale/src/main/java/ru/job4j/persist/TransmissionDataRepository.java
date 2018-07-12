package ru.job4j.persist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.job4j.Transmission;

@Component
public interface TransmissionDataRepository  extends CrudRepository<Transmission, Integer> {
}
