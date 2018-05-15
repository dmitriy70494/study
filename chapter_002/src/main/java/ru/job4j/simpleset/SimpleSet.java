package ru.job4j.simpleset;


import ru.job4j.simplearraylist.DynamicArray;

import java.util.Iterator;

/**
 * класс SimpleSet. Реализация коллекции Set.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 15.05.2018
 */
public class SimpleSet<E> implements Iterable<E> {

    /**
     * Обернутый саморасширяемый массив
     */
    private final DynamicArray<E> list;

    /**
     * Инициализирует список
     */
    public SimpleSet() {
        list = new DynamicArray<E>(100);
    }

    /**
     * Проверяет содержит ли список добавляемый элемент, если содержит, то удаляет его
     * @param e
     */
    public void add(E e) {
        Iterator<E> iterator = list.iterator();
        boolean access = true;
        while (iterator.hasNext()) {
            if (e.equals(iterator.next())) {
                access = false;
                break;
            }
        }
        if (access) {
            list.add(e);
        }
    }

    /**
     * Возвращает итератор данного массива. Возвращает только заполненные ячейки массива.
     *
     * @return Итератор
     */
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
