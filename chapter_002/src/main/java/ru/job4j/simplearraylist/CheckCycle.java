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
     * check - это проверочный элемент в системе
     * cursor - передвигающийся вперед
     * count - количество шагов методом next
     * мы запускаем бесконечный цикл
     * делаем в цикле заданное count количество шагов при условии что они есть, если пришло нул, значит зацикливания нет
     * далее увеличиваем количество шагов чтобы можно было попасть в цикл и проверить его
     * проверяем равна ли ссылка от cursor проверяемой, от которой курсор постоянно уходит
     * если равна то прекращаем цикл, так как есть зацикленность
     * и приставляем к нашему шагу проверочный элемент
     * Таким образом рано или поздно check войдет в зацикленный участок и там инкрементируемый cursor рано или
     * поздно дойдет до него, что будет означать зацикленность этого участка.
     * Но честно говоря было бы намного проще, если бы было известно количество элементов в этой связке.
     *
     * @param first
     * @return
     */
    public boolean hasCycle(Node first) {
        Node<T> check = first;
        Node<T> cursor = first;
        int count = 1;
        boolean access = true;
        while (access) {
            for (int index = 0; index < count; index++) {
                if (cursor == null) {
                    access = false;
                    break;
                }
                cursor = cursor.next;
            }
            count++;
            if (cursor == check) {
                break;
            }
            check = cursor;
        }
        return access;
    }

    public Node<T> buildNode() {
        return this.new Node<T>();
    }

    public class Node<T> {
        T value;
        Node<T> next;
    }

}
