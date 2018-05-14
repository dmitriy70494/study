package ru.job4j.simplearray;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

    /**
     * Тесты для класса UserStore.
     *
     * @author Dmitriy Balandin (d89086362742@yandex.ru)
     * @version $Id$
     * @since 14.05.2018
     */
    public class UserStoreTest {

        UserStore<User> us;

        @Before
        public void setUp() {
            us = new UserStore<User>(10);
            us.add(new User("11"));
            us.add(new User("12"));
        }

        @Test
        public void whenReplace() {
            us.replace("11", new User("14"));
            assertThat(us.findById("14").getId(), is("14"));
        }

        @Test
        public void whenDelete() {
            us.delete("12");
            Role result = null;
            assertThat(us.findById("12"), is(result));
        }



    }
