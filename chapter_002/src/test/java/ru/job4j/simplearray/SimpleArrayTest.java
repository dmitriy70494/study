package ru.job4j.simplearray;

import org.junit.Before;
import org.junit.Test;

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
public class SimpleArrayTest {

    SimpleArray<String> sa;
    @Before
    public void setUp() {
        sa = new SimpleArray<String>(10);
        sa.add("Лена");
        sa.add("Дима");
        sa.add("Ваня");
        sa.add("Андрей");
        sa.add("Маша");
    }

    @Test
    public void whenAddElseName() {
        sa.add("Вова");
        assertThat(sa.get(5), is("Вова"));
    }

    @Test
    public void whenAddFiveName() {
        sa.set(5, "Оля");
        assertThat(sa.get(5), is("Оля"));
    }

    @Test
    public void whenDeleteFiveName() {
        sa.delete(5);
        String except = null;
        assertThat(sa.get(5), is(except));
    }

    @Test (expected = NoSuchElementException.class)
    public void  hasNextNextSequentialInvocationAndShouldThrowNoSuchElementException() {
        Iterator<String> it = sa.iterator();
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
        it.next();
    }
}