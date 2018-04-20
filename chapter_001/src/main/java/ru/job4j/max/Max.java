package ru.job4j.max;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 19.04.2018
 */
public class Max {
    /**
     * Определяет максимальное число.
     * @param first первое число.
     * @param second второе число.
     * @return Ответ.
     */
    public int max(int first, int second) {

        return first > second ? first : second;
    }

    /**
     * Определяет максимальное число.
     * @param first первое число.
     * @param second второе число.
     * @param third второе число.
     * @return Ответ.
     */
    public int max(int first, int second, int third) {
        int temp = this.max(first, second);
        return this.max(temp, third);
    }

}
