package ru.job4j.array;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class BubbleSort {

    /**
     * Сортирует массив методом "Пузырька".
     * @param array начальный массив.
     * @return array отсортированный массив.
     */
    public int[] sort(int[] array) {
        int size = array.length - 1;
        int temp;
        for (int s = 0; s < size; s++) {
            for (int i = 0; i < size; i++) {
               if (array[i] > array[i + 1]) {
                   temp = array[i];
                   array[i] = array[i + 1];
                   array[i + 1] = temp;
               }
            }
        }
        return array;
    }
}
