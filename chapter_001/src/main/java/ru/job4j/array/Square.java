package ru.job4j.array;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class Square {
    /**
     * заполняет массив через цикл элементами от 1 до bound возведенными в квадрат
     * @param bound число, влияет на высоту массива
     * @return array массив c элементами от 1 до bound возведенными в квадрат.
     */
    public int[] calculate(int bound) {
        int[] array = new int[bound];
        for (int i = 1; i <= bound; i++) {
            array[i - 1] = i * i;
        }
        // заполнить массив через цикл элементами от 1 до bound возведенными в квадрат
        return array;
    }
}