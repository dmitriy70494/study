package ru.job4j.array;

/**
 * Объединяет 2 отсортированных массива в 1 отсортированный
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class TwoArraySort {

    /**
     * Объединяет 2 отсортированных массива в 1 отсортированный
     * Вравниваем начальные элементы
     * если меньше записываем в общий массив
     * затем попеременно записываем, пока не закончатся элементы в одном из массивов, если закончились
     * дописываем остаток элементов оставшегося массива.
     *
     * @param one начальный первый массив.
     * @param two начальный второй массив.
     * @return arrayAll объединенный массив.
     */
    public int[] arraySort(int[] one, int[] two) {
        int i = 0;
        int j = one.length;
        int n = 0;
        int m = two.length;
        int start = 0;
        int[] array = new int[j + m];
        while (i != j && n != m) {
            if (one[i] >= two[n]) {
                array[start++] = two[n++];
            } else {
                array[start++] = one[i++];
            }
        }
        while (i != j) {
            array[start++] = one[i++];
        }
        while (n != m) {
            array[start++] = two[n++];
        }
        return array;
    }
}