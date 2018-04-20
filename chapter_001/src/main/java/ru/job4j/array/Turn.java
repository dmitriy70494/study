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
        int length = array.length;
        int s = length-- / 2;
        for (int i = 0; i < s; i++) {
            int temp = array[i];
            array[i] = array[length - i];
            array[length - i] = temp;
        }
        return array;
    }
}