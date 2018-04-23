package ru.job4j.array;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class Turn {
    /**
     * Меняет местами числа в массиве.
     * @param array начальный массив.
     * @return array преобразованный массив.
     */
    public int[] turn(int[] array) {
        int size = array.length;
        int s = size-- / 2;
        for (int i = 0; i < s; i++) {
            int temp = array[i];
            array[i] = array[size - i];
            array[size - i] = temp;
        }
        return array;
    }
}