package ru.job4j.tracker;

import java.io.IOException;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Содержит методы, которыми можно работать с данными. Все данные хранятся в списке объектов Item
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 24.04.2018
 */
public class Tracker {

    /**
     * позволяет генерировать случайное число
     */
    private static final Random RN = new Random();

    /**
     * Список со всеми заявками пользователя
     */
    private List<Item> items = new ArrayList<Item>();

    /**
     * Данный метод генерирует уникальный ID, Добавляет его в экземпляр Item и добавляет его в
     * таблицу для хранения данных программы. Всего можно добавить 99 элементов (связано с работой метода Delete)
     * @param item Новый элемент с данными.
     * @return item возвращает добавленный элемент с присвоенным ID
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }


    /**
     * Изменяет ячейку массива. Поиск ячейки по Id
     * @param id id добавленного элемента.
     * @param item сам элемент с изменениями, может быть новым, так как метод поменяет id, который указан в первом параметре
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < this.items.size(); index++) {
            if (id.equals(this.items.get(index).getId())) {
                item.setId(id);
                this.items.set(index, item);
                break;
            }
        }
    }

    /**
     * Удаляет элемент с заданным id и сдвигает все элементы влево. Если нет такого id, то ничего не делает
     * @param id id элемента, который нужно удвалить.
     */
    public void delete(String id) {
        for (int index = 0; index < this.items.size(); index++) {
            if (id.equals(this.items.get(index).getId())) {
                this.items.remove(index);
                break;
            }
        }
    }

    /**
     * Возвращает список со всеми элементами, без пустых ячеек
     * @return copyItems список элементов, null - если список пуст
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Ищет все совпадения по имени, если находит возвращает список элементов
     * @param key поле name Item.
     * @return find список найденных, если нет найденных то список пустой
     */
    public List<Item> findByName(String key) {
        List<Item> find = new ArrayList<Item>();
        for (Item item : items) {
            if (key.equals(item.getName())) {
                find.add(item);
            }
        }
        return find;
    }

    /**
     * Ищет элемент по id и возвращает его
     * @param id id элемента, который нужно найти.
     * @return item если нет такого элемента, возвратит null
     */
    public Item findById(String id) {
        Item find = null;
        for (Item item : items) {
            if (id.equals(item.getId())) {
                find = item;
                break;
            }
        }
        return find;
    }

    /**
     * Генерирует случайное число на основании даты
     * @return String число в строке длиной long
     */
    public String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
    }

    /**
     * дает таблицу
     * @return items таблица с данными
     */
    public List<Item> getItems() {
        return items;
    }
}
