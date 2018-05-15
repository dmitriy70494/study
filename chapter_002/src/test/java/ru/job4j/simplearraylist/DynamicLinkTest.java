package ru.job4j.simplearraylist;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * тест класса DynamicLink. Проверяет итератор на правильность выдачи данных + реализовацию fail-fast поведения,
 * т.е. если с момента создания итератора коллекция подверглась структурному изменению, итератор должен кидать
 * ConcurrentModificationException. .
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 15.05.2018
 */
public class DynamicLinkTest {
    private DynamicLink<Integer> list;

    @Before
    public void beforeTest() {

        list = new DynamicLink<Integer>();

        list.add(1);

        list.add(2);

        list.add(3);

    }


    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));

    }

    /**
     * Тест на получение всех элементов
     */
    @Test
    public void hasNextNextSequentialInvocation() {
        Iterator<Integer> it = list.iterator();
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(3));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(2));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(1));
        MatcherAssert.assertThat(it.hasNext(), is(false));
    }

    /**
     * Тест на ошибку при изменении во время работы итератора
     */
    @Test(expected = ConcurrentModificationException.class)
    public void hasNextNextSequentialInvocationAndShouldThrowConcurrentModificationException() {
        Iterator<Integer> it = list.iterator();
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(3));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        list.add(4);
        it.next();
    }
}