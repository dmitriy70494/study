package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleArrayIterator implements Iterator<Integer> {

    /**
     * Индекс прохода по высоте
     */
    private int index = 0;

    /**
     * Индекс прохода по строке
     */
    private int cursor = 0;

    /**
     * массив
     */
    private int[][] doubleMas;

    /**
     * Конструктор инициализирует все необходимые параметры, двумерный массив, размер массива по длине, размер по высоте
     * если массив пуст, то ширина задается как 0, но тернарной операцией во избежание Исключения выхода за пределы массива
     *
     * @param doubleMas изначальный двумерный массив
     */
    public DoubleArrayIterator(int[][] doubleMas) {
        this.doubleMas = doubleMas;
    }

    /**
     * Метод проверяет можно ли считать число, если нельзя, выбрасывает исключение
     * затем считывает число переводя корретку дальше
     *
     * @return следующее число в массиве
     */
    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return this.doubleMas[this.index][this.cursor++];
    }

    /**
     * Определяет не закончился ли массив и есть ли следующий элемент, переводит корретку по высоте, если закончился
     * вложенный массив.
     *
     * @return true элемент есть, false элемента нет
     */
    @Override
    public boolean hasNext() {
        while (this.index < doubleMas.length && this.cursor == doubleMas[this.index].length) {
            this.index++;
            this.cursor = 0;
        }
        return this.index < doubleMas.length;
    }
}
