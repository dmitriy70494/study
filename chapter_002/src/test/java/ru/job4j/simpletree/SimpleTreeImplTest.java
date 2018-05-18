package ru.job4j.simpletree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * тест класса SimpleTreeImpl.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 18.05.2018
 */
public class SimpleTreeImplTest {
    @Test
    public void when6ElFindLastThen6() {
        SimpleTree<Integer> tree = new SimpleTreeImpl<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        SimpleTree<Integer> tree = new SimpleTreeImpl<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    /**
     * Тест итератора
     */
    @Test
    public void when6ElAndUsedIterator() {
        SimpleTree<Integer> tree = new SimpleTreeImpl<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void when6ElBynary() {
        SimpleTreeImpl<Integer> tree = new SimpleTreeImpl<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(4, 5);
        tree.add(4, 6);
        tree.add(5, 7);
        tree.add(5, 8);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void when6ElNotBynary() {
        SimpleTreeImpl<Integer> tree = new SimpleTreeImpl<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(4, 5);
        tree.add(4, 6);
        tree.add(5, 7);
        tree.add(5, 8);
        tree.add(6, 9);
        tree.add(6, 10);
        tree.add(2, 12);
        tree.add(2, 13);
        tree.add(13, 14);
        tree.add(13, 15);
        tree.add(13, 16);
        assertThat(tree.isBinary(), is(false));
    }
}