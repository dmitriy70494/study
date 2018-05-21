package ru.job4j.simpleset;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import ru.job4j.simplearraylist.DynamicLink;

/**
 * класс SimpleLinkedSet. Создает список Set на основе связного списка
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 16.05.2018
 */
public class SimpleLinkedSet<T> implements Iterable<T> {

    /**
     * Связный список
     */
    DynamicLink<T> link = new DynamicLink<T>();

    /**
     * Колечество элементов
     */
    int size;

    /**
     * Добавление элемента, если объекты равны в методе equals, то метод его не добавит.
     * @param date
     */
    public void add(T date) {
        if (!link.contains(date)) {
            link.addFirst(date);
            this.size++;
        }
    }

    /**
     * Итератор данного списка
     * @return
     */
    public Iterator<T> iterator() {
        return link.iterator();
    }
}
