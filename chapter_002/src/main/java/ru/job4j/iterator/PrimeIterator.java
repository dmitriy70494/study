package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.BitSet;

/**
 * класс PrimeIterator. Реализация Итератора получающего простые числа.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 14.05.2018
 */
public class PrimeIterator implements Iterator<Integer> {

    /**
     * массив.
     */
    private final int[] mas;

    /**
     * Длина массива.
     */
    private final int length;

    /**
     * Индекс элемента на котором находится корретка метода next и hasNext.
     */
    private int cursor = 0;

    /**
     * Содержит все простые числа от 2 до самого максимального числа в массиве.
     */
    private BitSet primes;

    /**
     * Конструктор инициализирует все необходимые параметры,
     * Инициализирует обрабатываемый массив mas
     * Инициализирует длину массива для проверок следующего хода
     * Далее определяет максимальное число в массиве
     * BitSet заполняется всеми простыми числами от 2 до максимального числа в массиве включая его
     *
     * @param mas исходный массив
     */
    public PrimeIterator(int[] mas) {
        this.mas = mas;
        this.length = this.mas.length;

        int max = this.mas[0];
        for (int number : mas) {
            if (max < number) {
                max = number;
            }
        }

        primes = new BitSet(max + 1);
        for (int number = 2; number <= max; number++) {
            primes.set(number);
        }
        int prime = 2;
        while (prime * prime <= max) {
            if (primes.get(prime)) {
                int notPrime = prime * prime;
                while (notPrime <= max) {
                    primes.clear(notPrime);
                    notPrime += prime;
                }
            }
            prime++;
        }
    }

    /**
     * Метод проверяет можно ли считать число, если нельзя, выбрасывает исключение
     * затем считывает число переводя корретку дальше.
     *
     * @return следующее число в массиве
     */
    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return this.mas[this.cursor++];
    }

    /**
     * Определяет не закончился ли массив и есть ли следующий элемент
     * также проталкивает корретку, если число не содержится в списке простых чисел
     *
     * @return true элемент есть, false элемента нет
     */
    @Override
    public boolean hasNext() {
        boolean access = this.cursor != this.length;
        while (access && !primes.get(mas[cursor])) {
            access = ++this.cursor != this.length;
        }
        return access;
    }
}