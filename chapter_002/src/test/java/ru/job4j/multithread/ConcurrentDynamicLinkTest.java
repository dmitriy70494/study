package ru.job4j.multithread;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.simplearraylist.DynamicLink;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для класса ConcurrentDynamicLink.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 28.05.2018
 */
public class ConcurrentDynamicLinkTest {
    private ConcurrentDynamicLink<Integer> list;

    @Before
    public void beforeTest() {

        list = new ConcurrentDynamicLink<Integer>();

        list.addFirst(1);

        list.addFirst(2);

        list.addFirst(3);

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
        MatcherAssert.assertThat(it.hasNext(), is(false));
    }

    /**
     * Тест на ошибку при изменении во время работы итератора
     */
    @Test(expected = ConcurrentModificationException.class)
    public void hasNextNextSequentialInvocationAndShouldThrowConcurrentModificationException() {
        Iterator<Integer> it = list.iterator();
        list.addFirst(4);
        it.next();
    }
}