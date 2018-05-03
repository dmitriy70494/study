package ru.job4j.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса SortUserTest. Добавляем трех пользователей в список и проверяем отсортировались ли они
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 03.05.2018
 */
public class SortUserTest {

    SortUser sort = new SortUser();
    List<User> list;


    @Before
    public void loadOutput() {
        list = Arrays.asList(
                new User("Dmitriy", 30),
                new User("Ivan", 16),
                new User("Elena", 41),
                new User("Andrey", 10),
                new User("Mary", 9)
        );

    }

    @Test
    public void whenSortByAge() {
        Set<User> sortList = sort.sort(list);
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        assertThat(sortList.toString(), is(list.toString()));
    }

    @Test
    public void whenSortByLengthAge() {
        List<User> result = sort.sortNameLength(list);
        List<User> except = Arrays.asList(
                new User("Ivan", 16),
                new User("Mary", 9),
                new User("Elena", 41),
                new User("Andrey", 10),
                new User("Dmitriy", 30)
        );
        assertThat(result.toString(), is(except.toString()));
    }

    @Test
    public void whenSortByNameAndAge() {
        List<User> listUser = Arrays.asList(
                new User("Сергей", 25),
                new User("Иван", 16),
                new User("Сергей", 20),
                new User("Иван", 10),
                new User("Иван", 9)
        );
        List<User> result = sort.sortByAllFields(listUser);
        List<User> except = Arrays.asList(
                new User("Иван", 9),
                new User("Иван", 10),
                new User("Иван", 16),
                new User("Сергей", 20),
                new User("Сергей", 25)
        );
        assertThat(result.toString(), is(except.toString()));
    }
}
