package ru.job4j.simplearraylist;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * тест класса SimpleStack. Проверяет Stack на правильность выдачи данных
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 15.05.2018
 */
public class SimpleStackTest {
    private SimpleStack<Integer> stack;

    @Before
    public void beforeTest() {

        stack = new SimpleStack<Integer>();

        stack.push(1);

        stack.push(2);

        stack.push(3);

    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        Integer result = null;
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
        assertThat(stack.poll(), is(result));

    }

}