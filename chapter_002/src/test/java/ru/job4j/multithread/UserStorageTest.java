package ru.job4j.multithread;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * класс UserStorageTest.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 28.05.2018
 */
public class UserStorageTest {


    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        UserStorage users = new UserStorage();
        assertThat(users.add(new User(1, 100)), is(true));
        assertThat(users.add(new User(2, 100)), is(true));
        assertThat(users.size(), is(2));
        assertThat(users.update(new User(1, 50)), is(true));
        assertThat(users.update(new User(3, 50)), is(false));
        assertThat(users.transfer(1, 2, 50), is(true));
        assertThat(users.delete(new User(1, 0)), is(true));
        assertThat(users.size(), is(1));
    }
}