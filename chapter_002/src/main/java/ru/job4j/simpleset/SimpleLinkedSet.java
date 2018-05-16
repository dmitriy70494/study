package ru.job4j.simpleset;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * класс SimpleLinkedSet. Создает список Set на основе связного списка
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 16.05.2018
 */
public class SimpleLinkedSet<T> implements Iterable<T> {

    /**
     * Первый элемет
     */
    private Node<T> first;

    /**
     * Последний элемент
     */
    private Node<T> last;

    /**
     * Колечество элементов
     */
    int size;

    /**
     * количество модификаций
     */
    int modCount;

    /**
     * Добавление элемента, если объекты равны в методе equals, то метод его не добавит.
     * @param date
     */
    public void add(T date) {
        boolean access = true;
        Iterator it = this.iterator();
        while (it.hasNext()) {
            if (date.equals(it.next())) {
                return;
            }
        }
        Node<T> add = new Node<T>(date, this.first, null);
        this.first = add;
        if (this.first.next == null) {
            this.last = add;
        } else {
            this.first.next.prev = this.first;
        }
        this.modCount++;
        this.size++;
    }

    /**
     * Итератор данного списка
     * @return
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int checked = modCount;

            Node<T> result = first;

            public T next() {
                if (checked != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T date = result.date;
                result = result.next;
                return date;
            }

            public boolean hasNext() {
                return result != null;
            }
        };
    }

    /**
     * Класс хранящий ссылки на след элементы
     * @param <T>
     */
    private class Node<T> {
        T date;

        Node<T> next;

        Node<T> prev;

        public Node(T date, Node<T> next, Node<T> prev) {
            this.date = date;
            this.next = next;
            this.prev = prev;
        }
    }
}
