package ru.job4j.simpletree;

import java.util.*;

/**
 * класс SimpleMap. Создает список Map на основе массива с вычисляющимся индексом на основе хешa
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 17.05.2018
 */
public class SimpleTreeImpl<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * Определяет результат выполнения метода add, вынесена так как не смог засунуть локальную переменную в лямбда выражение
     */
    private boolean access = false;

    /**
     * Родитель
     */
    private Node<E> root;


    /**
     * размер дерева
     */
    private int size = 1;

    /**
     * Изменения дерава, проверяется в итераторе на изменеия в дереве
     */
    private int modCount = 0;

    /**
     * Конструктор
     * инициализирует первый элемент
     *
     * @param value
     */
    public SimpleTreeImpl(E value) {
        this.root = new Node<E>(value);
    }

    /**
     * Метод должен проверять количество дочерних элементов в дереве. Если их <= 2 - то дерево бинарное.
     * @return
     */
    public boolean isBinary() {
        this.access = true;
        return findBinary(root);
    }

    private boolean findBinary(Node<E> node) {
        List<Node<E>> list = node.leaves();
        if (!access || list.size() > 2) {
            access = false;
            return access;
        }
        for (Node<E> element : list) {
            findBinary(element);
        }
        return access;
    }

    /**
     * Добавляет в родитель элемент дочерние
     *
     * @param parent parent.
     * @param child  child.
     * @return
     */
    @Override
    public boolean add(E parent, E child) {
        this.access = false;
        this.findBy(parent).ifPresent(value ->
                {
                    this.access = value.add(new Node<E>(child));
                    if (this.access) {
                        size++;
                        modCount++;
                    }
                }
        );
        return this.access;
    }

    /**
     * Проходит по дереву возвращает элемент, если находит его
     *
     * @param value
     * @return
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Node<E> rsl = null;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = el;
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return Optional.ofNullable(rsl);
    }

    /**
     * Возвращает итератор для этого дерева
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * Очередь
             */
            Queue<Node<E>> data;

            /**
             * Первый элемент
             */
            Node<E> first;

            /**
             * Учтанавливает количество изменений на момент инициализации, если в процессе работы итератора они не совпадут
             * будет выброшена ошибка
             */
            int count = modCount;

            /**
             * Конструктор, добавляет в очередь первый элемент
             */ {
                this.data = new LinkedList<>();
                this.data.offer(root);
                this.first = root;
            }

            /**
             * Возвращает первый элемент из очереди + добавляет детей этого элемента в очередь.
             * @return
             */
            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (this.count != modCount) {
                    throw new ConcurrentModificationException();
                }
                this.first = this.data.poll();
                for (Node<E> child : this.first.leaves()) {
                    this.data.offer(child);
                }
                return first.getValue();
            }

            /**
             * Проверяет пута ли очередь.
             * @return
             */
            @Override
            public boolean hasNext() {
                return !data.isEmpty();
            }
        };
    }
}
