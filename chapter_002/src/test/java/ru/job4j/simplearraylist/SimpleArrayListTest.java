package ru.job4j.simplearraylist;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {

    private SimpleLinkedList<Integer> list;

    @Before
    public void beforeTest() {

        list = new SimpleLinkedList<>();

        list.add(1);

        list.add(2);

        list.add(3);

    }


    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));

    }


    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    /**
     * Тест на удаление элемента
     */
    @Test (expected = NullPointerException.class)
    public void whenAddThreeElementsThenDeleteOne() {
        assertThat(list.get(0), is(3));
        list.delete();
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(1));
        list.get(2);
    }
}