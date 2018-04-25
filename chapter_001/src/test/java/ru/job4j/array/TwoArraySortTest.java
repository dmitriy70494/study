package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class TwoArraySortTest {
    @Test
    public void twoArraySort345To() {
        TwoArraySort sort = new TwoArraySort();
        int[] one = {2, 3, 4, 4, 4};
        int[] two = {1, 2, 4, 10, 11, 57};
        int[] result = sort.arraySort(one, two);
        int[] except = {1, 2, 2, 3, 4, 4, 4, 4, 10, 11, 57};
        assertThat(result, is(except));
    }

    @Test
    public void twoArraySort345To1() {
        TwoArraySort sort = new TwoArraySort();
        int[] one = {1, 2, 4, 10, 11, 57};
        int[] two = {2, 3, 4, 4};
        int[] result = sort.arraySort(one, two);
        int[] except = {1, 2, 2, 3, 4, 4, 4, 10, 11, 57};
        assertThat(result, is(except));
    }

    @Test
    public void twoArraySort345To2() {
        TwoArraySort sort = new TwoArraySort();
        int[] one = {1, 2, 4, 10, 11, 57};
        int[] two = {0};
        int[] result = sort.arraySort(one, two);
        int[] except = {0, 1, 2, 4, 10, 11, 57};
        assertThat(result, is(except));
    }

    @Test
    public void twoArraySort345To3() {
        TwoArraySort sort = new TwoArraySort();
        int[] one = {1, 2, 4, 10, 11, 57};
        int[] two = {6};
        int[] result = sort.arraySort(one, two);
        int[] except = {1, 2, 4, 6, 10, 11, 57};
        assertThat(result, is(except));
    }

    @Test
    public void twoArraySort345To4() {
        TwoArraySort sort = new TwoArraySort();
        int[] one = {1, 2, 4, 10, 11, 57};
        int[] two = {58};
        int[] result = sort.arraySort(one, two);
        int[] except = {1, 2, 4, 10, 11, 57, 58};
        assertThat(result, is(except));
    }

    @Test
    public void twoArraySort345To5() {
        TwoArraySort sort = new TwoArraySort();
        int[] one = {0};
        int[] two = {1, 2, 4, 10, 11, 57};
        int[] result = sort.arraySort(one, two);
        int[] except = {0, 1, 2, 4, 10, 11, 57};
        assertThat(result, is(except));
    }

    @Test
    public void twoArraySort345To6() {
        TwoArraySort sort = new TwoArraySort();
        int[] one = {6};
        int[] two = {1, 2, 4, 10, 11, 57};
        int[] result = sort.arraySort(one, two);
        int[] except = {1, 2, 4, 6, 10, 11, 57};
        assertThat(result, is(except));
    }

    @Test
    public void twoArraySort345To7() {
        TwoArraySort sort = new TwoArraySort();
        int[] one = {58};
        int[] two = {1, 2, 4, 10, 11, 57};
        int[] result = sort.arraySort(one, two);
        int[] except = {1, 2, 4, 10, 11, 57, 58};
        assertThat(result, is(except));
    }
}
