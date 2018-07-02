package ru.job4j.item;

import java.sql.Timestamp;

public class Item {

    private Integer id;

    private String desc;

    private Timestamp created;

    private String done;

    public Item() {

    }

    public Item(Integer id, String desc, Timestamp created, String done) {
        this.id = id;
        this.desc = desc;
        this.created = created;
        this.done = done;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return String.format("{\"%s\" : \"%s\", \"%s\" : \"%s\", \"%s\" : \"%s\", \"%s\" : \"%s\"}", "id", id, "desc", desc, "created", created, "done", done);
    }
}
