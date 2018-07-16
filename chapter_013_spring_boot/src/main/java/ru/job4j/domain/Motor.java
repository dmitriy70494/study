package ru.job4j.domain;

import javax.persistence.*;

@Entity
@Table(name="motor")
public class Motor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column (name = "name")
    private String name;

    public Motor() {
    }

    public Motor(Integer id) {
        this.id = id;
    }

    public Motor(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("{\"%s\" : \"%s\", \"%s\" : \"%s\"}", "id", id, "name", name);
    }
}
