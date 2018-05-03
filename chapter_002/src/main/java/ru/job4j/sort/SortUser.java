package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * класс SortUser. Сортирует объекты с данными пользователя.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 03.05.2018
 */
public class SortUser {

    /**
     * Из обычного списка преобразует в отсортированный TreeSet
     * @param list список
     * @return отсортированный по имени TreeSet
     */
    public Set<User> sort (List<User> list) {
        Set<User> users = new TreeSet<User>(list);
        return users;
    }
}
