package ru.job4j.comparator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Тесты для класса ListCompare. Проверяем работу самописного компаратора.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 04.05.2018
 */
    public class ListCompareTest {

        @Test
        public void whenStringsAreEqualThenZero() {
            ListCompare compare = new ListCompare();
            int rst = compare.compare(
                    "Ivanov",
                    "Ivanov"
            );
            assertThat(rst, is("Ivanov".compareTo("Ivanov")));
        }

        @Test
        public void whenLeftLessThanRightResultShouldBeNegative() {
            ListCompare compare = new ListCompare();
            int rst = compare.compare(
                    "Ivanov",
                    "Ivanova"
            );
            assertThat(rst, is("Ivanov".compareTo("Ivanova")));
        }

        @Test
        public void whenLeftGreaterThanRightResultShouldBePositive() {
            ListCompare compare = new ListCompare();
            int rst = compare.compare(
                    "Petrov",
                    "Ivanova"
            );
            assertThat(rst, is("Petrov".compareTo("Ivanova")));
        }

        @Test
        public void secondCharOfLeftGreaterThanRightShouldBePositive() {
            ListCompare compare = new ListCompare();
            int rst = compare.compare(
                    "Petrov",
                    "Patrov"
            );
            assertThat(rst, is("Petrov".compareTo("Patrov")));
        }

        @Test
        public void secondCharOfLeftLessThanRightShouldBeNegative() {
            ListCompare compare = new ListCompare();
            int rst = compare.compare(
                    "Patrova",
                    "Petrov"
            );
            assertThat(rst, is("Patrova".compareTo("Petrov")));
        }
    }