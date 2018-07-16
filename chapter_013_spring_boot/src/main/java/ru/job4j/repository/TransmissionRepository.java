package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Transmission;

@Repository
public interface TransmissionRepository extends CrudRepository<Transmission, Integer> {
}
