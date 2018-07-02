package ru.job4j;

public class Car {

    private Integer id;

    private String name;

    private Motor motor;

    private Transmission transmission;

    private Bodywork bodywork;

    public Car() {
    }

    public Car(Integer id) {
        this.id = id;
    }

    public Car(Integer id, String name, Motor motor, Transmission transmission, Bodywork bodywork) {
        this.id = id;
        this.name = name;
        this.motor = motor;
        this.transmission = transmission;
        this.bodywork = bodywork;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Bodywork getBodywork() {
        return bodywork;
    }

    public void setBodywork(Bodywork bodywork) {
        this.bodywork = bodywork;
    }
}
