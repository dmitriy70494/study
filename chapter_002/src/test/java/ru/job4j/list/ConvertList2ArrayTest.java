package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса ConvertList2Array.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 01.05.2018
 */
public class ConvertList2ArrayTest {

    @Test
    public void when9ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        assertThat(result, is(expect));
    }

        @Test
        public void when7ElementsThen9() {
            ConvertList2Array list = new ConvertList2Array();
            int[][] result = list.toArray(
                    Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                    3
            );
            int[][] expect = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 0, 0}
            };
            assertThat(result, is(expect));
        }
}
