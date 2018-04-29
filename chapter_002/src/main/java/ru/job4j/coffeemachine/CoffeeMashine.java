package ru.job4j.coffeemachine;

/**
 * Класс определяет количество сдачи, выданной минимальным числом монет.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 29.04.2018
 */
public class CoffeeMashine {

    /**
     * Количество монет нужного номинала
     */
    private int[] coins = {10, 5, 2, 1};

    /**
     * Массив для подсчета количества монет каждого номинала
     */
    private int[] count = new int[coins.length];

    /**
     * Массив с монетами, возвращаемый вызывающему методу
     */
    private int[] surrender;

    /**
     * Общее количество монет
     */
    private int position = 0;

    /**
     * Метод определяет хватит ли денег на кофе, нет вернет нулевой массив
     * Определяет количество сдачи в общем выражении
     * Затем считает сколько монет каждого номинала должно быть
     * делим сначала на 10, число записываем в массив, в сумму передаем остаток от деления и так далее до 1 рубля
     * затем создаем массив нужно размера, размер определяла переменная position
     * и в двойном цикле заполняем значения монет.
     * Возвращаем готовый массив
     * Можно было реализовать через коллекцию ArrayList, возможно было бы меньше переменных и кода
     * @param value = купюра. 50 100 и тд.
     * @param price = цена кофе
     * @return surrender Метод вернет массив {10, 5} = 15 рублей
     *
     */
    public int[] changes(int value, int price) {
        if (price > value) {
            return new int[0];
        }
        int surrender = value - price;
        for (int index = 0; index < this.coins.length; index++) {
            this.count[index] = surrender / this.coins[index];
            surrender %= this.coins[index];
            this.position += this.count[index];
        }
        this.surrender = new int[this.position--];
        for (int index = this.coins.length - 1; index >= 0; index--) {
            while (this.count[index] != 0) {
                this.surrender[position--] = coins[index];
                this.count[index]--;
            }
        }
        return this.surrender;
    }
}
