package ru.job4j.simpleset;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для класса SimpleSet.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 15.05.2018
 */
public class SimpleSetTest {

    SimpleSet<String> ss;
    Iterator<String> it;



    @Before
    public void setUp() {
        ss = new SimpleSet<String>();
        ss.add("Лена");
        ss.add("Дима");
        ss.add("Ваня");
        ss.add("Андрей");
        ss.add("Маша");
        it = ss.iterator();
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Лена"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Дима"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Ваня"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Андрей"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Маша"));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void hasAddEqualsNameAndNextNextSequentialInvocation() {
        ss.add("Маша");
        ss.add("Маша");
        ss.add("Маша");
        ss.add("Маша");
        ss.add("Маша");
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Лена"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Дима"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Ваня"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Андрей"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Маша"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void hasNextNextSequentialInvocationAndShouldThrowConcurrentModificationException() {
        Iterator<String> it = ss.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Лена"));
        assertThat(it.hasNext(), is(true));
        ss.add("Слава");
        it.next();
    }
}