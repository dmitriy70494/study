package ru.job4j.iterator;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator {

    /**
     * высота массива
     */
    private int matrixSize;

    /**
     * Длина строки массива
     */
    private int lineSize;

    /**
     * Индекс прохода по высоте
     */
    private int matrixIndex = 0;

    /**
     * Индекс прохода по строке
     */
    private int lineIndex = 0;

    /**
     * массив
     */
    private int[][] matrix;

    Integer result;

    /**
     * Конструктор инициализирует все необходимые параметры, размер массива по длине, размер по высоте, и вспомогательный массив line,
     * который хранит в себе данные вложенных массивов. Если массив пуст, выбрасывает исключение NoSuchElementException.
     *
     * @param matrix
     */
    public MatrixIterator(int[][] matrix) {
        this.matrix = matrix;
        this.matrixSize = this.matrix.length;
        this.lineSize = this.matrix.length == 0 ? 0 : this.matrix[0].length;
        this.nextStep();
    }

    private void nextStep() {
        while (this.lineIndex == this.lineSize) {
            this.lineIndex = 0;
            if (++this.matrixIndex >= this.matrixSize) {
                break;
            }
            this.lineSize = this.matrix[this.matrixIndex].length;
        }
    }

    /**
     * Метод проверяет можно ли считать число, если нельзя, выбрасывает исключение
     * затем считывает число переводя корретку дальше
     * отдельный метод проверяет, закончился ли вложенный массив, если да, то переводит корретку на новую строку, до
     * тех пов пока высота массива не закончится
     *
     * @return следующее число в массиве
     */
    public Integer next() {
        if (this.matrixIndex >= this.matrixSize) {
            throw new NoSuchElementException();
        }
        this.result = this.matrix[this.matrixIndex][this.lineIndex++];
        this.nextStep();
        return this.result;
    }

    /**
     * Определяет не закончился ли массив и есть ли следующий элемент
     * @return true элемент есть, false элемента нет
     */
    public boolean hasNext() {
        return this.matrixIndex < this.matrixSize;
    }
}
