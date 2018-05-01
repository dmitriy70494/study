package ru.job4j.search;

/**
 * класс Task. Содержит информацию о задаче, также геттеры, и конструктор
 * для инициализации объекта
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 01.05.2018
 */
public class Task {

    /**
     * Задача, которую нужно выполнить
     */
    private String desc;

    /**
     * приоритет задачи
     */
    private int priority;

    /**
     * Конструктор инициализирует параметры задачи
     * @param desc задача
     * @param priority приоритет
     */
    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    public String getDesc() {
        return desc;
    }

    public int getPriority() {
        return priority;
    }
}
