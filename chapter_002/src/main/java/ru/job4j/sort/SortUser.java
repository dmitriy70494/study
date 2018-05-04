package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

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
    public Set<User> sort(List<User> list) {
        Set<User> users = new TreeSet<User>(list);
        return users;
    }

    /**
     *  Этот метод определяет Comparator для метода Collections.sort и сортирует List<User> по длине имени.
     *
     * @param list неотсортированный список пользователей
     * @return отсортированный список
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(final User o1, final User o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        });
        return list;
    }

    /**
     * В этом методе определяется Comparator для метода Collections.sort и отсортировывается List<User> по обоим полям,
     * сначала сортировка по имени в лексикографическом порядке, потом по возрасту.
     *
     * @param list неотсортированный список пользователей
     * @return отсортированный список
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(final User o1, final User o2) {
                final int comparison = o1.getName().compareTo(o2.getName());
                return comparison == 0 ? Integer.compare(o1.getAge(), o2.getAge()) : comparison;
            }
        });
        return list;
    }
}
