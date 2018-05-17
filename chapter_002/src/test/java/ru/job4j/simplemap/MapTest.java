package ru.job4j.simplemap;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.simplemap.User;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для класса HashMap.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 16.05.2018
 */
public class MapTest {

    Map<User, String> map;
    User first;
    User second;

    @Before
    public void setUp() {
        map = new HashMap<User, String>();
        first = new User("Dima", 1, null);
        second = new User("Dima", 1, null);
        map.put(first, "first");
        map.put(second, "second");

    }

    /**
     * Добавилось два объекта так как хешкод у объектов разный, то программа соотнесла их в разные
     * хештаблицы
     */
    @Test
    public void whenAddTwoElementAndEqualsFalseHashCodFalseShouldTwoInMap() {
        System.out.println("first hash: " + first.hashCode());
        System.out.println("second hash: " + second.hashCode());
        System.out.println("equals: " + first.equals(second));
        System.out.println(map);
    }

    /**
     * По прежнему добавилось два объекта так как хешкод у объектов одинаковый, то программа проверяет объекты на равенство методом equals
     * если он говорит что объекты все же разные, то тогда в ячейку с одним хешключом кладутся 2 объекта.
     * результаты:
     * first hash: 66035240
     * second hash: 66035240
     * equals: false
     * {ru.job4j.simplemap.User@3ef9e28=first, ru.job4j.simplemap.User@3ef9e28=second}
     */
    @Test
    public void whenAddTwoElementAndEqualsFalseHashCodTrueShouldTwoInMap() {
        System.out.println("first hash: " + first.hashCode());
        System.out.println("second hash: " + second.hashCode());
        System.out.println("equals: " + first.equals(second));
        System.out.println(map);
    }
}