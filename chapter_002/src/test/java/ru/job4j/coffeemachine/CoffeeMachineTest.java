package ru.job4j.coffeemachine;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса кофемашины.
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 29.04.2018
 */

public class CoffeeMachineTest {

    /**
     * Проверка если положили в автомат 100 рублей а кофе стоит 32 рубля
     */
    @Test
    public void whenUser100CoffeeBy32() {
        CoffeeMashine machine = new CoffeeMashine();
        int[] surrender = machine.changes(100, 32);
        assertThat(surrender, is(new int[]{10, 10, 10, 10, 10, 10, 5, 2, 1}));
    }
}
