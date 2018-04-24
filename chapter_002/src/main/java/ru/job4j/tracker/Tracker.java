package ru.job4j.tracker;

import java.io.IOException;

/**
 * Содержит методы, которыми можно работать с данными. Все данные хранятся в списке объектов Item
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 24.04.2018
 */
public class Tracker {
    private Item[] items = new Item[100];

    /**
     * Данный метод генерирует уникальный ID, Добавляет его в экземпляр Item и добавляет его в
     * таблицу для хранения данных программы. Всего можно добавить 99 элементов (связано с работой метода Delete)
     * @param item Новый элемент с данными.
     * @return item возвращает добавленный элемент с присвоенным ID
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        if(item != null){
            for (int i = 0; i < items.length-1; i++) {
                if (items[i] == null) {
                    items[i] = item;
                    return item;
                }
            }
            return null;
        }
        return null;
    }

    /**
     * Изменяет ячейку массива. Поиск ячейки по Id
     * @param id id добавленного элемента.
     * @param item сам элемент с изменениями, может быть новым, так как метод поменяет id, который указан в первом параметре
     */
    public void replace(String id, Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i]!=null && items[i].getId().equals(id)) {
                item.setId(id);
                items[i] = item;
                break;
            }
        }
    }

    /**
     * Удаляет элемент с заданным id и сдвигает все элементы влево. Если нет такого id, то ничего не делает
     * @param id id элемента, который нужно удвалить.
     */
    public void delete(String id) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getId().equals(id)) {
                System.arraycopy(items, i + 1, items, i, items.length - i - 1);
                break;
            }
        }
    }

    /**
     * Возвращает список со всеми элементами, без пустых ячеек
     * @return copyItems список элементов, null - если список пуст
     */
    public Item[] findAll() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                Item[] copyItems = new Item[i];
                System.arraycopy(items, 0, copyItems, 0, i);
                return copyItems;
            }
        }
        return null;
    }

    /**
     * Ищет все совпадения по имени, если находит возвращает список элементов
     * @param key поле name Item.
     * @return find список найденных, если нет найденных то список пустой
     */
    public Item[] findByName(String key) {
        int count = 0;
        Item[] finded = new Item[100];
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getName().equals(key)) {
                finded[count] = items[i];
                count++;
            }
        }
        Item[] find = new Item[count];
        System.arraycopy(finded, 0, find, 0, count);
        return find;
    }

    /**
     * Ищет элемент по id и возвращает его
     * @param id id элемента, который нужно найти.
     * @return item если нет такого элемента, возвратит null
     */
    public Item findById(String id) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getId().equals(id)) {
                return items[i];
            }
        }
        return null;
    }

    /**
     * Генерирует случайное число на основании даты
     * @return String число в строке длиной long
     */
    public String generateId() {
        int randomOne = (int) (Math.random() * 10000 + Math.random() * 100 + Math.random() * 10);
        long currentTime = System.nanoTime();
        int randomTwo = (int) (Math.random() * 10000 + Math.random() * 100 + Math.random() * 10);
        return randomTwo + "" + currentTime + "" + randomOne;
    }

    /**
     * дает таблицу
     * @return items таблица с данными
     */
    public Item[] getItems() {
        return items;
    }


}
