package ru.job4j.simplemap;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * тест класса SimpleMap.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 16.05.2018
 */

public class SimpleMapTest {

    private SimpleMap<Integer, String> map;

    @Before
    public void beforeTest() {
        map = new SimpleMap<Integer, String>();
        map.insert(1, "first");
        map.insert(2, "second");
        map.insert(3, "third");

    }

    @Test
    public void whenAddThreeElementsThenDeleteIt() {
        String result = null;
        assertThat(map.delete(3), is(true));
        assertThat(map.delete(1), is(true));
        assertThat(map.delete(2), is(true));
        assertThat(map.delete(3), is(false));
        assertThat(map.get(1), is(result));
        assertThat(map.get(2), is(result));
        assertThat(map.get(3), is(result));
        map.insert(1, "first");
        assertThat(map.get(1), is("first"));
    }

    @Test
    public void whenAddThreeElementsThenAddElseEqualsElementsUseConteins() {
        assertThat(map.insert(1, "first"), is(false));
        assertThat(map.insert(2, "first"), is(false));
        assertThat(map.insert(3, "first"), is(false));
        assertThat(map.insert(2, "first"), is(false));
        assertThat(map.insert(1, "first"), is(false));
        assertThat(map.insert(4, "four"), is(true));
        assertThat(map.insert(5, "five"), is(true));
        assertThat(map.get(3), is("third"));
        assertThat(map.get(2), is("second"));
        assertThat(map.get(1), is("first"));
        assertThat(map.get(4), is("four"));
        assertThat(map.get(5), is("five"));
    }
}