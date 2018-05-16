package ru.job4j.simpleset;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * тест класса SimpleLinkedSet.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 16.05.2018
 */

public class SimpleLinkedSetTest {

    private SimpleLinkedSet<Integer> set;

    @Before
    public void beforeTest() {
        set = new SimpleLinkedSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);

    }

    @Test
    public void whenAddThreeElementsThenUseIterator() {
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAddThreeElementsThenAddElseEqualsElementsUseIterator() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);
        set.add(4);
        set.add(3);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenAddThreeElementsThenUseIteratorAndThrowException() {
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(3));
        set.add(4);
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

}