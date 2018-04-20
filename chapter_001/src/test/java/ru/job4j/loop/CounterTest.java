package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class CounterTest {

    @Test
    public void sumEvenNumberInStartToFinish() {
        Counter counter = new Counter();
        assertThat(counter.add(1, 11), is(30));
    }
}
