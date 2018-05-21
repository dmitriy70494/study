package ru.job4j.simpletree;

import java.util.*;

/**
 * класс SimpleBinaryTree. Создает BST - binary search tree (двоичное дерево поиска).
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 18.05.2018
 */


public class SimpleBinaryTree<E extends Comparable<E>> implements Iterable<E> {

    /**
     * верхушка дерева
     */
    private Node<E> root;

    /**
     * Размер коллекции
     */
    private int size = 0;

    /**
     * модификации
     */
    private int modCount = 0;

    /**
     * Указывает корень, родителя и ветку, верхушка всегда false
     *
     * @param value
     * @return
     */
    public boolean add(E value) {
        Node<E> next = root;
        Node<E> parent = null;
        addNode(value, next, parent, false);
        size++;
        modCount++;
        return size != 1 ? next != root && parent != null : root != null;
    }

    /**
     * рекурсивно проверяет есть ли пустой элемент, если элемент не пуст, то сравниваем объекты
     * если значение больше то отправляем в правую ветку с идентификатором true, меньше или равно, то в левую ветку
     * если root = null то добавляем элемент в верхушку
     * @param value
     * @param next
     * @param parent
     * @param branch
     */
    public void addNode(E value, Node<E> next, Node<E> parent, boolean branch) {
        if (next == null) {
            next = new Node(value, parent, branch);
            if (root == null) {
                root = next;
            } else if (branch) {
                parent.right = next;
            } else {
                parent.left = next;
            }
            return;
        }
        if (next.value.compareTo(value) > 0) {
            parent = next;
            next = next.left;
            addNode(value, next, parent, false);
        } else {
            parent = next;
            next = next.right;
            addNode(value, next, parent, true);
        }
    }

    /**
     * Итератор
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator() {

            /**
             * Указывает на первый элемент
             */
            Node<E> next = root;

            /**
             * Количество уже выданных результатов, если индекс будет равен размеру дерева, то метод hasNext будет выдавать false
             */
            int index = 0;

            /**
             * Проверяет не изменилось ли дерево
             */
            int count = modCount;

            /**
             * Конструктор переносит курсор в самое лево на самый маленький элемент дерева
             */
            {
                while (next != null && next.left != null) {
                    next = next.left;
                }
            }

            /**
             * проверяет есть ли ход и не изменилось ли дерево
             * берет результат
             * сдвигает указатель на следующий элемент
             * если правый элемент есть, то один шаг направо, потом до упора налево
             * если нет правого элемента, то шаг вверх налево или направо в зависосмости от ветки, идентификатор ветки находится
             * в Node boolean branch
             * индексирует индекс для проверки на наличие следующего элемента
             * @return
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (count != modCount) {
                    throw new ConcurrentModificationException();
                }
                E result = next.value;
                if (next.right != null) {
                    next = next.right;
                    while (next.left != null) {
                        next = next.left;
                    }
                } else {
                    while (next.branch) {
                        next = next.parant;
                    }
                    next = next.parant;
                }
                index++;
                return result;
            }

            /**
             * Проверяет не закончились ли элементы в коллекции
             * @return
             */
            @Override
            public boolean hasNext() {
                return index != size;
            }
        };
    }

    /**
     * Элемент дерева
     * @param <E>
     */
    class Node<E> {

        /**
         * ссылка на родитель ветки
         */
        Node<E> parant;

        /**
         * левый меньший или равный элемент
         */
        Node<E> left;

        /**
         * правый больший
         */
        Node<E> right;

        /**
         * идентификатор родителя ветки правый true, левый false
         */
        boolean branch = false;

        /**
         * Значение
         */
        E value;

        /**
         * Добавляет значение и родителя, + идентификатор
         * @param value
         * @param parant
         * @param branch
         */
        public Node(E value, Node<E> parant, boolean branch) {
            this.value = value;
            this.parant = parant;
            this.branch = branch;
        }
    }
}