package ru.job4j.multithread;

/**
 * Класс Base. Создает модель.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 01.06.2018
 */
public class Base {

    /**
     * уникальный номер модели
     */
    private final int id;

    /**
     * текущая версия изменений модели
     */
    private int version;

    /**
     * Имя модели
     */
    private String name;

    public Base(int id, int version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public int getVersion() {
        return this.version;
    }

    public String getName() {
        return this.name;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setName(String name) {
        this.name = name;
    }
}
