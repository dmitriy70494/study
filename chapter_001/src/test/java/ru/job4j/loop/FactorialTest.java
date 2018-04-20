package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {
    @Test
    public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
        Factorial factorial = new Factorial();
        assertThat(factorial.calc(5), is(120));
        //тест, проверяющий, что факториал для числа 5 равен 120.
    }

    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        Factorial factorial = new Factorial();
        assertThat(factorial.calc(0), is(1));
        //тест, проверяющий, что факториал для числа 0 равен 1.
    }
}