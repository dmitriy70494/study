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
        int[] a = {1, 2, 4, 10, 11, 57};
        int[] b = {3, 4, 5};
        int[] result = sort.arraySort(a, b);
        int[] except = {1, 2, 3, 4, 4, 5, 10, 11, 57};
        assertThat(result, is(except));
    }
}
