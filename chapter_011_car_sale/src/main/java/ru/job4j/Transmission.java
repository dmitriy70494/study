package ru.job4j;

public class Transmission {

    private Integer id;

    private String name;

    public Transmission() {
    }

    public Transmission(Integer id) {
        this.id = id;
    }

    public Transmission(Integer id, String name) {
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
}
