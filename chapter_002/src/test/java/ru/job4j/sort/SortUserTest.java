package ru.job4j.sort;

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

    @Test
    public void whenFindByName() {
        SortUser sort = new SortUser();
        List<User> list = Arrays.asList(
                new User("Dmitriy", 30),
                new User("Ivan", 16),
                new User("Elena", 41),
                new User("Andrey", 10),
                new User("Mary", 9)
        );
        Set<User> sortList = sort.sort(list);
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        assertThat(sortList.toString(), is(list.toString()));
    }
}
