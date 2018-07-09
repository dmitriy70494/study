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

    private CarStore store;
    private MotorStore motorStore;
    private TransmissionStore transStore;
    private BodyworkStore bodyStore;
    private UserStore userStore;
    private Car car;

    @Before
    public void onLoad() {
        this.store = CarStore.getInstance();
        this.motorStore = MotorStore.getInstance();
        this.transStore = TransmissionStore.getInstance();
        this.bodyStore = BodyworkStore.getInstance();
        this.userStore = UserStore.getInstance();
        Motor motor = new Motor(1, "motor");
        Transmission trans = new Transmission(1, "trans");
        Bodywork body = new Bodywork(1, "body");
        User user = new User(1, "admin", "admin");
        this.motorStore.add(motor);
        this.transStore.add(trans);
        this.bodyStore.add(body);
        this.userStore.add(user);
        this.car = new Car(1, "aka", motor, trans, body, true, new Timestamp(System.currentTimeMillis()), user, "update/image.jpg");
        this.store.add(this.car);
    }

    @After
    public void postLoad() throws IOException {
        this.store.close();
        this.motorStore.close();
        this.transStore.close();
        this.bodyStore.close();
        this.userStore.close();
    }

    @Test
    public void whenAddCarInStore() throws IOException {
        assertThat(this.car.toString(), is(this.store.findByID(1).toString()));
    }

    @Test
    public void whenAllCar() throws IOException {
        String expect = String.format("[%s]", this.car.toString());
        assertThat(expect, is(this.store.findAll().toString()));
    }

    @Test
    public void whenFindCarByID() throws IOException {
        assertThat(this.car.toString(), is(this.store.findByID(1).toString()));
    }

    @Test
    public void whenFindCarLastDay() throws IOException {
        String expect = String.format("[%s]", this.car.toString());
        assertThat(expect, is(this.store.findCarLastDay().toString()));
    }

    @Test
    public void whenFindCarWithFoto() throws IOException {
        String expect = String.format("[%s]", this.car.toString());
        assertThat(expect, is(this.store.findCarWithFoto().toString()));
    }

    @Test
    public void whenFindAllCarsName() throws IOException {
        String expect = String.format("[%s]", this.car.getName());
        assertThat(expect, is(this.store.findAllCarsName().toString()));
    }
}
