package ru.job4j.trading;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * тест класса DepthOfMarkets.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 16.05.2018
 */
public class DepthOfMarketsTest {

    private DepthOfMarkets dom;
    private StringBuffer buffer;

    @Before
    public void beforeTest() {
        dom = new DepthOfMarkets(100);
        buffer = new StringBuffer(500);
        dom.processOrder(new Order(2, 1, true, true, 11, 10));
        dom.processOrder(new Order(3, 1, true, false, 10, 9));
        dom.processOrder(new Order(4, 1, true, false, 12, 9));
    }

    @Test
    public void whenAdd17ElementsThenPrintDOM() {
        assertThat(dom.printDOM(1), is(buffer
                        .append(String.format("%10s%10s%10s%n", "Покупка", "Цена", "Продажа"))
                        .append(String.format("%10s%10s%10s%n", "", "12.0", "9"))
                        .append(String.format("%10s%10s%10s%n", "1", "11.0", "")).toString()
                )
        );

    }

    @Test
    public void whenAdd17ElementsThenDelete2ElementsPrintDOM() {
        dom.processOrder(new Order(2, 1, false, true, 11, 1));
        dom.processOrder(new Order(4, 1, false, false, 12, 9));
        assertThat(dom.printDOM(1), is(buffer
                        .append(String.format("%10s%10s%10s%n", "Покупка", "Цена", "Продажа")).toString()
                )
        );
    }

    @Test
    public void whenAdd17ElementsThenAdd3ElementsEqualsPricePrintDOM() {
        dom.processOrder(new Order(3, 1, true, true, 11, 9));
        dom.processOrder(new Order(5, 1, true, false, 12, 1));
        assertThat(dom.printDOM(1), is(buffer
                        .append(String.format("%10s%10s%10s%n", "Покупка", "Цена", "Продажа"))
                        .append(String.format("%10s%10s%10s%n", "", "12.0", "10"))
                        .append(String.format("%10s%10s%10s%n", "10", "11.0", "")).toString()
                )
        );
    }
}