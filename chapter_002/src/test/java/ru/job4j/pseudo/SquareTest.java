package ru.job4j.pseudo;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для прорисовки квадрата
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 26.04.2018
 */
public class SquareTest {

    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        assertThat(
                square.draw(),
                is(
                        new StringBuilder()
                                .append("++++++++\n")
                                .append("++++++++\n")
                                .append("++++++++\n")
                                .append("++++++++\n")
                                .toString()
                )
        );
    }
}
