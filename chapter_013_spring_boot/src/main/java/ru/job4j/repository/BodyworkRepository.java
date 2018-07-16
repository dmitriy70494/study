package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Bodywork;

@Repository
public interface BodyworkRepository extends CrudRepository<Bodywork, Integer> {
}
