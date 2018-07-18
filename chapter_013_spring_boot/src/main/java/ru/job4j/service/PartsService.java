package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.repository.BodyworkRepository;
import ru.job4j.repository.MotorRepository;
import ru.job4j.repository.TransmissionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PartsService {

    MotorRepository motorStore;
    TransmissionRepository transStore;
    BodyworkRepository bodyStore;

    @Autowired
    public PartsService(MotorRepository motorStore, TransmissionRepository transStore, BodyworkRepository bodyStore) {
        this.motorStore = motorStore;
        this.transStore = transStore;
        this.bodyStore = bodyStore;
    }

    @Transactional(readOnly = true)
    public List<Iterable<?>> findAllParts() {
        List<Iterable<?>> list = new ArrayList<>();
        list.add(this.motorStore.findAll());
        list.add(this.transStore.findAll());
        list.add(this.bodyStore.findAll());
        return list;
    }
}
