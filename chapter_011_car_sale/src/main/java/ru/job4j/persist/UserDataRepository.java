package ru.job4j.persist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.job4j.Bodywork;

@Component
public interface UserDataRepository  extends CrudRepository<Bodywork, Integer> {
}
