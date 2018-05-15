package ru.job4j.simplearraylist;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * тест класса SimpleQueue. Проверяет очередь на правильность выдачи данных
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 15.05.2018
 */

public class SimpleQueueTest {

    private SimpleQueue<Integer> queue;

    @Before
    public void beforeTest() {
        queue = new SimpleQueue<Integer>();
        queue.push(1);
        queue.push(2);
        queue.push(3);

    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        Integer result = null;
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
        assertThat(queue.poll(), is(result));

    }

}