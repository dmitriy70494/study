package ru.job4j.array;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class Matrix {

    /**
     * Coздает таблицу умножения заданного размера в массиве массивов.
     * mn1 - первый множитель.
     * mn2 - второй множитель.
     * @param size ширина и высота массива.
     * @return table двухмерный массив с таблицей умножения.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int mn1 = 0; mn1 < size; mn1++) {
            for (int mn2 = 0; mn2 < size; mn2++) {
                table [mn1][mn2] = (mn1 + 1) * (mn2 + 1);
            }
        }
        return table;
    }
}
