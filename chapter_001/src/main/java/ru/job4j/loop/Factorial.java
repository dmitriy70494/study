package ru.job4j.loop;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class Factorial {
    /**
     * Определяет факториал числа.
     * @param n число.
     * @return result факториал числа.
     */
    public int calc(int n) {
        int result = 1;
        for (int i = n; i > 0; i--) {
            result *= i;
        }
        return result;
    }
}
