package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 19.04.2018
 */

public class MaxTest {
    @Test
    public void whenFirstLessSecond(){
        Max isMax = new Max();
        assertThat(isMax.max(1,2),is(2));
    }
}
