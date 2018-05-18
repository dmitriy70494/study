package ru.job4j.simpletree;

import java.util.Optional;

/**
 * класс SimpleMap. Создает список Map на основе массива с вычисляющимся индексом на основе хешa
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 17.05.2018
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     *
     * @param parent parent.
     * @param child  child.
     * @return
     */
    boolean add(E parent, E child);

    /**
     * Ищет элемент в дереве.
     * @param value
     * @return
     */
    Optional<Node<E>> findBy(E value);
}