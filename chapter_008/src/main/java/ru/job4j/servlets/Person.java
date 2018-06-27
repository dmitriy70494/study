package ru.job4j.servlets;

import java.util.Objects;

public class Person {

    private String id;

    private String name;

    private String lastname;

    private String sex;

    private String description;

    private String country;

    private String city;

    public Person(String id, String name, String lastname, String sex, String description, String country, String city) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.sex = sex;
        this.description = description;
        this.country = country;

        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(lastname, person.lastname) &&
                Objects.equals(sex, person.sex) &&
                Objects.equals(description, person.description) &&
                Objects.equals(country, person.country) &&
                Objects.equals(city, person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, sex, description, country, city);
    }
}
