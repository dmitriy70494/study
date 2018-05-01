package ru.job4j.search;

import java.util.LinkedList;

/**
 * класс PriorityQueue. Содержит информацию о задачах, также метод добавляющий задачу с учетом ее приоритета,
 * и метод возвращающий наиприоритетнейшую задачу.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 01.05.2018
 */
public class PriorityQueue {

    /**
     * Список всех задач
     */
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        int index;
        for(index = 0; index < tasks.size(); index++) {
            if (tasks.get(index).getPriority() > task.getPriority()) {
                break;
            }
        }
        tasks.add(index, task);
    }

    /**
     * Возвращает задачу первую в списке
     *
     * @return задачу
     */
    public Task take() {
        return this.tasks.poll();
    }
}