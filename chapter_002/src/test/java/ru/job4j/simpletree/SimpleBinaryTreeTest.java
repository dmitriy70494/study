package ru.job4j.simpletree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * тест класса SimpleBinaryTree.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 18.05.2018
 */
public class SimpleBinaryTreeTest {


    /**
     * Тест итератора
     */
    @Test
    public void when6ElAndUsedIterator() {
        SimpleBinaryTree<Integer> tree = new SimpleBinaryTree<>();
        tree.add(12);
        tree.add(3);
        tree.add(14);
        tree.add(5);
        tree.add(16);
        tree.add(8);
        tree.add(100);
        tree.add(0);
        tree.add(2);
        tree.add(7);
        tree.add(9);
        tree.add(6);
        tree.add(5);
        tree.add(3);
        tree.add(19);
        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(8));
        assertThat(it.next(), is(9));
        assertThat(it.next(), is(12));
        assertThat(it.next(), is(14));
        assertThat(it.next(), is(16));
        assertThat(it.next(), is(19));
        assertThat(it.next(), is(100));
        assertThat(it.hasNext(), is(false));
    }


}