package ru.job4j.simpleset;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * тест класса SimpleHashTableSet.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 16.05.2018
 */
public class SimpleHashTableSetTest {

    private SimpleHashTableSet<Integer> set;

    @Before
    public void beforeTest() {
        set = new SimpleHashTableSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);

    }

    @Test
    public void whenAddThreeElementsThenDeleteIt() {
        assertThat(set.remove(3), is(true));
        assertThat(set.remove(1), is(true));
        assertThat(set.remove(2), is(true));
        assertThat(set.remove(3), is(false));
        assertThat(set.conteins(3), is(false));
        assertThat(set.conteins(2), is(false));
        assertThat(set.conteins(1), is(false));
        set.add(1);
        assertThat(set.conteins(1), is(true));
    }

    @Test
    public void whenAddThreeElementsThenAddElseEqualsElementsUseConteins() {
        assertThat(set.add(1), is(false));
        assertThat(set.add(2), is(false));
        assertThat(set.add(3), is(false));
        assertThat(set.add(1), is(false));
        assertThat(set.add(4), is(true));
        assertThat(set.add(3), is(false));
        assertThat(set.add(2), is(false));
        assertThat(set.conteins(3), is(true));
        assertThat(set.conteins(2), is(true));
        assertThat(set.conteins(1), is(true));
        assertThat(set.conteins(4), is(true));
        assertThat(set.conteins(5), is(false));
    }
}