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
     * Хранит количество изменений
     */
    private int modCount;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E date) {
        Node<E> newLink = new Node<E>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
        this.modCount = this.modCount != 2000000000 ? ++this.modCount : 0;
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

        Node(E date) {
            this.date = date;
        }
    }
}
