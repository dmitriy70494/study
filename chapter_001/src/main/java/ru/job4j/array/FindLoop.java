package ru.job4j.array;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class FindLoop {
    /**
     * Поиск числа в массиве
     * если числа нет в массиве, то возвращаем -1.
     * если есть то возвращаем порядковый номер в массиве совпавшего элемента
     * @param array начальный массив.
     * @return array отсортированный массив.
     */
    public int indexOf(int[] array, int number) {
        int element = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                element = i;
                break;
            }
        }
        return element;
    }
}