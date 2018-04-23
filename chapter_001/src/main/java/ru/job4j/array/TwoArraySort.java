package ru.job4j.array;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class TwoArraySort {
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