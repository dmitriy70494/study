package ru.job4j.tracker;

/**
 * Все данные хранятся в полях класса. Содержит геттеры и сеттеры
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 24.04.2018
 */

public class Item {

    /**
     * Поле для хранения id заявки
     */
    private String id;

    /**
     * Поле для хранения названия заявки
     */
    private String name;

    /**
     * Поле для хранения описания заявки
     */
    private String desc;

    /**
     * Поле для хранения времени создания или изменения заявки
     */
    private long created;

    /**
     * Поле для хранения комментариев к заявке
     */
    private String[] comments = new String[20];

    /**
     * Конструтор для инициализации заявки.
     * @param name - имя заявки
     * @param desc - описание заявки
     * @param created - время создания заявки
     * @param comments - комментарии заявки
     */
    public Item(String name, String desc, long created, String[] comments) {
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.comments = comments;
    }

    /**
     * Конструтор для инициализации заявки.
     * @param name - имя заявки
     * @param desc - описание заявки
     * @param created - время создания заявки
     */
    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    /**
     * Конструтор для инициализации заявки без параметров.
     */
    public Item() {
    }

    public String toString() {
        return "Номер ID заявки: " + id + "\nНазвание заявки: " + name + "\nОписание заявки: " + desc + "\n\n";
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
