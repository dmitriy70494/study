package ru.job4j.simplearraylist;

/**
 * класс SimpleQueue. Создает очередь перый вошел последний вышел
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 15.05.2018
 */
public class SimpleQueue<T> {
    /**
     * Первый объект
     */
    private Node<T> first;

    /**
     * Последний объект
     */
    private Node<T> last;

    /**
     * Метод вставляет элемент в начало очереди.
     */
    public void push(T value) {
        Node<T> one = first;
        Node<T> newLink = new Node<T>(value, first, null);
        this.first = newLink;
        if (one == null) {
            this.last = newLink;
        } else {
            one.prev = this.first;
        }
    }


    /**
     * Метод удаляет первый элемент в очереди и возвращает его.
     */
    public T poll() {
        T date = null;
        if (last != null) {
            date = last.date;
            last.date = null;
            this.last = this.last.prev;
        }
        return date;
    }

    /**
     * Класс предназначен для хранения данных и ссылок на предыдущий и следующий объекты.
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
