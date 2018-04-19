package ru.job4j.calculator;

/**
 * Конвертер валюты.
 * @author Balandin Dmitriy (d89086362742@yandex.ru)
 * @since 19.4.2018
 */
public class Converter {

    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return result Евро.
     */
    public int rubleToEuro(int value) {
        return value / 70;
    }

    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return result Доллоры.
     */
    public int rubleToDollar(int value) {
        return value / 60;
    }

    /**
     * Конвертируем евро в рубли.
     * @param value Евро.
     * @return result Рубли.
     */
    public int euroToRuble(int value) {
        return value * 70;
    }

    /**
     * Конвертируем доллары в рубли.
     * @param value Доллары.
     * @return result Рубли.
     */
    public int dollarToRuble(int value) {
        return value * 60;
    }

}
