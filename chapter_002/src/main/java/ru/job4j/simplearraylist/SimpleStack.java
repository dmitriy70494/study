package ru.job4j.simplearraylist;

/**
 * класс SimpleStack. Создает Stack перый вошел первый вышел.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 15.05.2018
 */
public class SimpleStack<T> {

    /**
     * Обертка для хранения ссылок на следующий объект
     */
    private Node<T> first;

    /**
     * Метод вставляет элемент в начало очереди.
     */
    public void push(T value) {
        Node<T> newLink = new Node<T>(value);
        newLink.next = this.first;
        this.first = newLink;
    }


    /**
     * Метод удаляет первый элемент в очереди и возвращает его.
     */
    public T poll() {
        T date = null;
        if (first != null) {
            date = first.date;
            first.date = null;
            this.first = this.first.next;
        }
        return date;
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
