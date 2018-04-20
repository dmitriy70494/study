package ru.job4j.loop;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class Counter {
    /**
     * Определяет сумму четных чисел из диапазона.
     * @param start первое число.
     * @param finish второе число.
     * @return Ответ.
     */
    public int add(int start, int finish) {
        int result = 0;
        for (int i = start; i <= finish; i++) {
           if (i % 2 == 0) {
               result += i;
           }
        }
        return result;
    }
}
