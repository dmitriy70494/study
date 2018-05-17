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

    /**
     * По прежнему добавилось два объекта так как хешкод у объектов разный, то программа разносит объекты по разным ячейкам, не проводя сравнение методом equals
     * результаты:
     * first hash: 1343441044
     * second hash: 693632176
     * equals: true
     * {ru.job4j.simplemap.User@50134894=first, ru.job4j.simplemap.User@2957fcb0=second}
     */
    @Test
    public void whenAddTwoElementAndEqualsTrueHashCodFalseShouldTwoInMap() {
        System.out.println("first hash: " + first.hashCode());
        System.out.println("second hash: " + second.hashCode());
        System.out.println("equals: " + first.equals(second));
        System.out.println(map);
    }

    /**
     * Добавился сначала первый объект, затем, когда начал добавляться второй объект User программа вычислила его хешфункцию,
     * и определила ее ячейку. Эта ячейка занята, тогда программа стала сравнивать эти объекты методом equals. Он выдал true, тогда заменила предыдущее значение
     * новым в этом же ключе. на что указано ниже User@3ef9e28=SECOND а не first как было раньше.
     * результаты:
     * first object: ru.job4j.simplemap.User@3ef9e28
     * second object: ru.job4j.simplemap.User@3ef9e28
     * first hash: 66035240
     * second hash: 66035240
     * equals: true
     * {ru.job4j.simplemap.User@3ef9e28=second}
     */
    @Test
    public void whenAddTwoElementAndEqualsTrueHashCodTrueShouldTwoInMap() {
        System.out.println("first object: " + first);
        System.out.println("second object: " + second);
        System.out.println("first hash: " + first.hashCode());
        System.out.println("second hash: " + second.hashCode());
        System.out.println("equals: " + first.equals(second));
        System.out.println(map);
    }
}