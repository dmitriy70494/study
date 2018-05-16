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
     * Связанный список
     */
    DynamicLink<T> linked;

    /**
     * Инициализирует список
     */
    public SimpleQueue() {
        linked = new DynamicLink<T>();
    }

    /**
     * Метод вставляет элемент в начало очереди.
     */
    public void push(T value) {
        linked.addFirst(value);
    }

    /**
     * Метод удаляет последний элемент в очереди и возвращает его.
     */
    public T poll() {
        return linked.removeLast();
    }
}
