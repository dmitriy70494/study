package ru.job4j.multithread;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.simplearraylist.DynamicArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для класса ConcurrentDynamicArray.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 28.05.2018
 */
public class ConcurrentDynamicArrayTest {
    ConcurrentDynamicArray<String> da;

    @Before
    public void setUp() {
        da = new ConcurrentDynamicArray<String>(3);
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
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void hasNextNextSequentialInvocationAndShouldThrowConcurrentModificationException() {
        Iterator<String> it = da.iterator();
        da.add("Костя");
        it.next();
    }
}