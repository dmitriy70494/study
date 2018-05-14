package ru.job4j.simplearray;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для класса RoleStore.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 14.05.2018
 */
public class RoleStoreTest {

    RoleStore<Role> rs;

    @Before
    public void setUp() {
         rs = new RoleStore<Role>(10);
         rs.add(new Role("11"));
         rs.add(new Role("12"));
    }

    @Test
    public void whenReplace() {
        rs.replace("11", new Role("14"));
        assertThat(rs.findById("14").getId(), is("14"));
    }

    @Test
    public void whenDelete() {
        rs.delete("12");
        Role result = null;
        assertThat(rs.findById("12"), is(result));
    }
}