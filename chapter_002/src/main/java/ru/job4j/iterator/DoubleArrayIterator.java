package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleArrayIterator implements Iterator {

    /**
     * высота массива
     */
    private int height;

    /**
     * Длина строки массива
     */
    private int width;

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
        this.height = doubleMas.length;
        this.width = doubleMas.length == 0 ? 0 : doubleMas[0].length;
    }

    /**
     * Метод проверяет можно ли считать число, если нельзя, выбрасывает исключение
     * затем считывает число переводя корретку дальше
     *
     * @return следующее число в массиве
     */
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
    public boolean hasNext() {
        while (this.cursor == this.width && ++this.index < this.height) {
            this.cursor = 0;
            this.width = this.doubleMas[this.index].length;
        }
        return this.index < this.height;
    }
}
