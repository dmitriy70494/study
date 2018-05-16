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
     * Связанный список
     */
    DynamicLink<T> linked;

    /**
     * Инициализирует список
     */
    public SimpleStack() {
        linked = new DynamicLink<T>();
    }

    /**
     * Метод вставляет элемент в начало очереди.
     */
    public void push(T value) {
        linked.addFirst(value);
    }

    /**
     * Метод удаляет первый элемент в очереди и возвращает его.
     */
    public T poll() {
        return linked.removeFirst();
    }
}
