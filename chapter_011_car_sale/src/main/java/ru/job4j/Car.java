package ru.job4j;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column (name = "done")
    private Boolean done;

    @Column (name = "create_date")
    private Timestamp create;

    @Column (name = "name")
    private String name;

    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinColumn(name="id_motor")
    private Motor motor;

    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinColumn(name="id_transmission")
    private Transmission transmission;

    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinColumn(name="id_bodywork")
    private Bodywork bodywork;

    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinColumn(name="id_user")
    private User user;

    @Column (name = "foto")
    private String foto;

    public Car() {
    }

    public Car(Integer id) {
        this.id = id;
    }

    public Car(Integer id, String name, Motor motor, Transmission transmission, Bodywork bodywork, Boolean done, Timestamp create, User user, String foto) {
        this.id = id;
        this.name = name;
        this.motor = motor;
        this.transmission = transmission;
        this.bodywork = bodywork;
        this.done = done;
        this.create = create;
        this.user = user;
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreate() {
        return create;
    }

    public void setCreate(Timestamp create) {
        this.create = create;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
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

    @Override
    public String toString() {
        return String.format("{\"id\" : %s, \"name\" : \"%s\", \"motor\" : %s, \"transmission\" : %s, \"bodywork\" : %s, \"foto\" : \"%s\", \"create\" : \"%s\", \"done\" : \"%s\"}",
                id, name, motor.toString(), transmission.toString(), bodywork.toString(), foto, create, done);
    }
}