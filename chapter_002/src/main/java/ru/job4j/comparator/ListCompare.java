package ru.job4j.comparator;

import java.util.Comparator;
import java.util.List;

/**
 * класс ListCompare. Реализация компаратора.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 03.05.2018
 */
public class ListCompare implements Comparator<String> {

    @Override
    /**
     * Сравнение двух списков, т.е. сравниваем элементы двух String.value, находящихся на
     * одних и тех же позициях (по одним и тем же индексом). Сравнение в лексикографическом порядке.
     * Если строки совпадают, но одна больше другой, возвращаем разницу строк в количестве символов.
     *
     * @return различие в несовпадающего символа в лексикографическом порядке.
     */
    public int compare(String left, String right) {
        int result = 0;
        int leftlen = left.length();
        int rightlen = right.length();
        int size = leftlen > rightlen ? rightlen : leftlen;
        for (int index = 0; index < size; index++) {
            result = Character.compare(left.charAt(index), right.charAt(index));
            if (result != 0) {
                break;
            }
        }
        return result != 0 ? result : leftlen - rightlen;
    }
}