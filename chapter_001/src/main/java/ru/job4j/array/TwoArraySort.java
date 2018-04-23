package ru.job4j.array;

/**
 * Объединяет 2 отсортированных массива в 1 отсортированный
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
     * @param arrayOne начальный первый массив.
     * @param arrayTwo начальный второй массив.
     * @return arrayAll объединенный массив.
     */
    public static int[] arraySort(int[] arrayOne, int[] arrayTwo) {
        int length = arrayOne.length + arrayTwo.length;
        int[] arrayAll = new int[length];
        for (int n = 0, i = 0, j = 0; n < length; n++) {
            if (arrayOne[i] < arrayTwo[j]) {
                arrayAll[n] = arrayOne[i];
                i++;
                if (i == arrayOne.length) {
                    n++;
                    while (j != arrayTwo.length) {
                        arrayAll[n++] = arrayTwo[j++];
                    }
                    break;
                }
            } else {
                arrayAll[n] = arrayTwo[j];
                j++;
                if (j == arrayTwo.length) {
                    n++;
                    while (i != arrayOne.length) {
                        arrayAll[n++] = arrayOne[i++];
                    }
                    break;
                }
            }
        }
        return arrayAll;
    }
}