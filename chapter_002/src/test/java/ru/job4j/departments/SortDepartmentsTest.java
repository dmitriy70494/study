package ru.job4j.departments;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Тесты для класса SortDepartments. Проверяем работу сортировщика департаментов.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 14.05.2018
 */
public class SortDepartmentsTest {
    String[] departments = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};

    @Test
    public void shouldReturnSortDepartments() {
        boolean result = true;
        String[] except = {"K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        SortDepartments sorted = new SortDepartments();
        String[] results = sorted.sort(departments);
        for (int index = 0; index < results.length; index++) {
            //System.out.println(results[index]);
            if (!results[index].equals(except[index])) {
                result = false;
            }
        }
        assertThat(result, is(true));
    }

    @Test
    public void shouldReturnReversSortDepartments() {
        boolean result = true;
        String[] except = {"K2",  "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2", "K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2"};
        SortDepartments sorted = new SortDepartments();
        String[] results = sorted.reverseSort(departments);
        for (int index = 0; index < results.length; index++) {
            //System.out.println(results[index]);
            if (!results[index].equals(except[index])) {
                result = false;
            }
        }
        assertThat(result, is(true));
    }
}
