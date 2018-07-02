package ru.job4j;

public class Bodywork {

    private Integer id;

    private String name;

    public Bodywork() {
    }

    public Bodywork(Integer id) {
        this.id = id;
    }

    public Bodywork(Integer id, String name) {
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
