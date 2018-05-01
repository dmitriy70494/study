package ru.job4j.search;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса PriorityQueue. Добавляем три задачи в список и проверяем
 * выдачу задач.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 01.05.2018
 */
public class PriorityQueueTest {

    /**
     * Список задач
     */
    PriorityQueue queue = new PriorityQueue();

    @Before
    public void loadOutput() {
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        queue.put(new Task("one", 0));
    }

    @Test
    public void whenHigherPriority() {
        Task result = queue.take();
        assertThat(result.getDesc(), is("one"));
    }

    @Test
    public void whenHigherPriorityTwo() {
        Task resultOne = queue.take();
        Task resultTwo = queue.take();
        assertThat(resultTwo.getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriorityThree() {
        Task resultOne = queue.take();
        Task resultTwo = queue.take();
        Task resultThree = queue.take();
        assertThat(resultThree.getDesc(), is("middle"));
    }

    @Test
    public void whenHigherPriorityFour() {
        Task resultOne = queue.take();
        Task resultTwo = queue.take();
        Task resultThree = queue.take();
        Task resultFour = queue.take();
        assertThat(resultFour.getDesc(), is("low"));
    }

}
