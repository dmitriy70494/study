package ru.job4j.simplearraylist;

/**
 * класс CheckCycle. Проверяет, есть ли зацикленность в связном списке
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 15.05.2018
 */
public class CheckCycle<T> {

    /**
     * Данный метод делает следующее.
     * Если зацикленность есть  черепаха догонит зайца, который зачтрял в цикле
     *
     * @param first
     * @return
     */
    public boolean hasCycle(Node first) {
        Node<T> turtle = first;
        Node<T> rabbit = first;
        while (rabbit != null && rabbit.next != null) {
            rabbit = rabbit.next.next;
            turtle = turtle.next;
            if (rabbit == turtle) {
                break;
            }
        }
        return rabbit != null;
    }

    public Node<T> buildNode() {
        return this.new Node<T>();
    }

    public class Node<T> {
        T value;
        Node<T> next;
    }

}
