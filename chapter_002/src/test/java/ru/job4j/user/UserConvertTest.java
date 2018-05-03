package ru.job4j.user;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса UserConvertTest.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 03.05.2018
 */

public class UserConvertTest {

    List<User> list = new ArrayList<User>();
    HashMap<Integer, User> result;

    @Before
    public void loadOutput() {
        UserConvert convert = new UserConvert();
        this.list.add(new User(1, "Dmitriy", "Sverdlovsk"));
        this.list.add(new User(2, "Ivan", "Sverdlovsk"));
        this.list.add(new User(3, "Denis", "Moskov"));
        this.list.add(new User(4, "Elena", "Leningrad"));
        this.result = convert.process(this.list);
    }

    @Test
    public void whenArray4UserThenHashMapFindId1() {
        assertThat(result.get(1).getName(), is("Dmitriy"));
    }

    @Test
    public void whenArray4UserThenHashMapFindId2() {
        assertThat(result.get(2).getName(), is("Ivan"));
    }

    @Test
    public void whenArray4UserThenHashMapFindId3() {
        assertThat(result.get(3).getName(), is("Denis"));
    }

    @Test
    public void whenArray4UserThenHashMapFindId4() {
        assertThat(result.get(4).getName(), is("Elena"));
    }
}
