package ru.job4j.tracker;
/**
 * Все данные хранятся в полях класса. Содержит геттеры и сеттеры
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 24.04.2018
 */

public class Item {
    private String id;
    private String name;
    private String desc;
    private long created;
    private String[] comments = new String[20];

    public Item(String name, String desc, long created, String[] comments) {
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.comments = comments;
    }

    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public Item() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public long getCreated() {
        return created;
    }

    public String[] getComments() {
        return comments;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }


}
