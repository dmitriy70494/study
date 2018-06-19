package ru.job4j.servlets;

import java.sql.Timestamp;

public class User {

    private int id;

    private String name;

    private String login;

    private String email;

    private Timestamp createDate;

    public User(String name, String login, String email, Timestamp createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public User(Integer id, String name, String login, String email, Timestamp createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder user = new StringBuilder("User{");
        user.append("id=").append(id);
        user.append(", name='").append(name).append('\'');
        user.append(", login='").append(login).append('\'');
        user.append(", email='").append(email).append('\'');
        user.append(", createDate=").append(createDate);
        user.append('}');
        return user.toString();
    }
}
