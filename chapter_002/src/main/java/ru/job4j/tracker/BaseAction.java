package ru.job4j.tracker;

/**
 * Абстрактный класс, дополняет действия конструктором, для инициализации значений
 * + реализует 2 одинаковых для всех классов метода
 * int key
 * String info
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public abstract class BaseAction implements UserAction {
    private final int key;
    private final String name;

    /**
     * Конструктор, задает начальные параметры действия
     * @param key   номер пункта меню в массиве
     * @param name название деяствия
     */
    protected BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Возвращает номер пункта меню действия
     * @return возвращает номер пункта меню действия
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Возвращает строку с названием пункта меню действия
     * @return возвращает строку с номером и названием пункта меню действия
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}
