package ru.job4j.simplearraylist;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для класса SimpleArray.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 14.05.2018
 */
public class DynamicArrayTest {

    DynamicArray<String> da;

    @Before
    public void setUp() {
        da = new DynamicArray<String>(3);
        da.add("Лена");
        da.add("Дима");
        da.add("Ваня");
        da.add("Андрей");
        da.add("Маша");
    }

    @Test
    public void whenAddElseName() {
        da.add("Вова");
        assertThat(da.get(5), is("Вова"));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        Iterator<String> it = da.iterator();
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
        Iterator<String> it = da.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Лена"));
        assertThat(it.hasNext(), is(true));
        da.add("Костя");
        it.next();
    }
}