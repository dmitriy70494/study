package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс ConvertList2Array. Делает из двумерного массива список
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 01.05.2018
 */
public class ConvertMatrix2List {
    /**
     * В метод приходит двумерный массив целых чисел, метод проходится
     * по всем элементам массива и добавляет их в List<Integer>
     *
     * @param array двумерный массив
     * @return list список
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] mas : array) {
            for (int number : mas) {
                list.add(number);
            }
        }
        return list;
    }
}
