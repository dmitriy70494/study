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

    }

    /**
     * Добавилось два объекта так как хешкод у объектов разный, то программа соотнесла их в разные
     * хештаблицы
     */
    @Test
    public void WhenAddTwoElementAndEqualsIHashcodNullShouldTwoInMap() {
        map.put(first, "first");
        map.put(second,"second");
        System.out.println("first hash: " + first.hashCode());
        System.out.println("second hash: " + second.hashCode());
        System.out.println("equals: " + first.equals(second));
        System.out.println(map);
    }
}