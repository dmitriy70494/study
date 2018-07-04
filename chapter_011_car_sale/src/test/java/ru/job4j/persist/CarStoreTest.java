package ru.job4j.persist;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.*;

import java.io.IOException;
import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CarStoreTest {

    CarStore store;
    Car car;

    @Before
    public void onLoad() {
        this.store = CarStore.getInstance();
    }

    @After
    public void postLoad() throws IOException {
        this.store.close();
    }

    @Test
    public void whenAddCarInStore() throws IOException {
        this.car = new Car(1, "aka", new Motor(1), new Transmission(1), new Bodywork(1), true, new Timestamp(System.currentTimeMillis()), new User(), "rg");
        store.add(car);
        store.delete(this.car);
        assertThat(car.getId() > 1, is(true));
    }

    @Test
    public void whenFindCarInStore() throws IOException {
        this.car = this.store.findByID(2);
        assertThat(car.getId(), is(2));
    }

    @Test
    public void whenAllCar() throws IOException {
        System.out.println(this.store.findAll().toString());
    }
}
