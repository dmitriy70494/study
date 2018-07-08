package ru.job4j.persist;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.*;

import java.io.IOException;
import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MotorStoreTest {

    MotorStore store;
    Motor motor;

    @Before
    public void onLoad() {
        this.store = MotorStore.getInstance();
    }

    @After
    public void postLoad() throws IOException {
    }

    @Test
    public void whenAddCarInStore() throws IOException {
        this.motor = new Motor(1, "aka");
        store.add(motor);
        assertThat(this.motor.toString(), is(this.store.findByID(1).toString()));
    }
}