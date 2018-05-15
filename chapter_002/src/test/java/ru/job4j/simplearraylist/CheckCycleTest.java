package ru.job4j.simplearraylist;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * тест класса CheckCycle. Проверяет зациклены или нет связанные элементы
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 15.05.2018
 */
public class CheckCycleTest {
    private CheckCycle<Integer> check = new CheckCycle<>();
    CheckCycle.Node first;

    @Before
    public void beforeTest() {
        this.first = this.check.buildNode();
        this.first.next = this.check.buildNode();
        this.first.next.next = this.check.buildNode();
        this.first.next.next.next = this.first;
    }

    @Test
    public void whenAddFourElementsThenCheckCicleWhichExist() {
        assertThat(this.check.hasCycle(this.first), is(true));
    }

    @Test
    public void whenAddFourElementsThenCheckNoCicle() {
        this.first.next.next.next = this.check.buildNode();;
        assertThat(this.check.hasCycle(this.first), is(false));
    }
}