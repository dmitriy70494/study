package ru.job4j.departments;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * класс SortDepartments. Сортировка департаментов.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 14.05.2018
 */
public class SortDepartments {

    /**
     * Данный метод добавляет в Коллекцию TreeSet все возможные недостающие отделы
     *
     * @param departments Массив Отделов
     * @param sortedDepartments Отсортированный массив отделов
     */
    private void addDepartment(String[] departments, TreeSet<String> sortedDepartments) {
        String[] unit;
        for (String department : departments) {
            unit = department.split("\\\\");
            if (unit.length == 2) {
                sortedDepartments.add(unit[0]);
            }
            if (unit.length == 3) {
                sortedDepartments.add(unit[0] + "\\" + unit[1]);
            }
        }
    }

    /**
     * Метод сначала преобразует массив в TreeSet, затем добавляет в него недостающие отделы
     * Затем используя естесственную сортировку TreeSet сортирует по возрастанию сама, потом преобразуем TreeSet в
     * Массив строк
     *
     * @param departments неотсортированный список отделов
     * @return отсортированный по возрастанию список отделов
     */
    public String[] sort(String[] departments) {
        TreeSet<String> sortedDepartments = new TreeSet<String>(Arrays.asList(departments));
        this.addDepartment(departments, sortedDepartments);
        return sortedDepartments.toArray(new String[sortedDepartments.size()]);
    }

    /**
     * Метод сначала создает TreeSet с компаратором, который делает нужную нам сортировку. Добавляем элементы массива в TreeSet,
     * затем добавляет в него недостающие отделы.
     * TreeSet сортирует по убыванию заложенным компаратором с самого добавления элементов,
     * потом преобразуем TreeSet в Массив строк.
     *
     * @param departments
     * @return
     */
    public String[] reverseSort(String[] departments) {
        TreeSet<String> sortedDepartments = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String left, String right) {
                int result = 0;
                String[] lefts = left.split("\\\\");
                String[] rights = right.split("\\\\");
                int length = lefts.length < rights.length ? lefts.length : rights.length;
                int index = 0;
                result = rights[index].compareTo(lefts[index++]);
                while (result == 0 && index < length) {
                    result = lefts[index].compareTo(rights[index++]);
                }
                return result == 0 ? lefts.length - rights.length : result;
            }
        });
        sortedDepartments.addAll(Arrays.asList(departments));
        this.addDepartment(departments, sortedDepartments);
        return sortedDepartments.toArray(new String[sortedDepartments.size()]);
    }
}
