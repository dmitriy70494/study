package ru.job4j.simplearraylist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * класс DynamicLink. Создает связный список.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 15.05.2018
 */
public class DynamicLink<E> implements Iterable<E> {

    /**
     * Размер коллекции
     */
    private int size;

    /**
     * Обертка для хранения ссылок на следующий объект
     */
    private Node<E> first;

    /**
     * Обертка для хранения ссылок на предыдущий объект
     */
    private Node<E> last;

    /**
     * Хранит количество изменений
     */
    private int modCount;

    /**
     * Метод вставляет данные в зависимости от аргумента в начало или в конец.
     */
    private void add(Node<E> added) {
        boolean preved = added.prev == null;
        boolean nexted = added.next == null;
        if (preved && nexted) {
            this.last = added;
            this.first = added;
        }
        if (!nexted) {
            this.first.prev = added;
            this.first = added;
        }
        if (!preved) {
            this.last.next = added;
            this.last = added;
        }
        ++this.size;
        ++this.modCount;
    }

    /**
     * Определяет содержится ли объект в очереди
     * @param date
     * @return
     */
    public boolean contains(E date) {
        boolean contains = false;
        Node<E> next = this.first;
        for (int index = 0; index < size; index++) {
            if (next.date.equals(date)) {
                contains = true;
                break;
            }
            next = next.next;
        }
        return contains;
    }

    /**
     * Метод вставляет в начало списка данные.
     */
    public void addFirst(E date) {
        this.add(new Node<E>(date, this.first, null));
    }

    /**
     * Метод вставляет в конце списка данные.
     */
    public void addLast(E date) {
        this.add(new Node<E>(date, null, this.last));
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Удаляет первый элемент
     * @return
     */
    public E removeFirst() {
        E removed = null;
        if (this.first != null) {
            removed = this.first.date;
            this.first = this.first.next;
            if (first != null) {
                first.prev = null;
            }
        }
        return removed;
    }

    /**
     * Удаляет последний элемент
     * @return
     */
    public E removeLast() {
        E removed = null;
        if (this.last != null) {
            removed = this.last.date;
            this.last = this.last.prev;
            if (last != null) {
                last.next = null;
            }
        }
        return removed;
    }

    /**
     * Если Экземпляр изменится при работе итератора, будет сгенерирована ошибка
     *
     * @return Итератор экземпляра данного списка
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> result = first;
            int expected = modCount;
            public E next() {
                if (expected != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E date = result.date;
                result = result.next;
                return date;
            }

            public boolean hasNext() {
                return result != null;
            }
        };
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {
        E date;
        Node<E> next;
        Node<E> prev;

        Node(E date, Node<E> next, Node<E> prev) {
            this.date = date;
            this.next = next;
            this.prev = prev;
        }
    }
}
