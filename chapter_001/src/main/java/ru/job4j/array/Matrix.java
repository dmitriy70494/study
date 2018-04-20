package ru.job4j.array;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class Matrix {

    /**
     * Coздает таблицу умножения заданного размера в массиве массивов.
     * @param size ширина и высота массива.
     * @return table двухмерный массив с таблицей умножения.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int s = 0; s < size; s++) {
            for (int i = 0; i < size; i++) {
                table [s][i] = (s + 1) * (i + 1);
            }
        }
        return table;
    }
}
