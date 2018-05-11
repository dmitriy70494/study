package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class EvenIterator implements Iterator {

    /**
     * Длина массива
     */
    private int length;

    /**
     * Индекс элемента на котором находится корретка метода next
     */
    private int cursor = 0;

    /**
     * массив
     */
    private int[] mas;

    Integer result;

    /**
     * Конструктор инициализирует все необходимые параметры, размер массива по длине, размер по высоте, и вспомогательный массив line,
     * который хранит в себе данные вложенных массивов. Если массив пуст, выбрасывает исключение NoSuchElementException.
     *
     * @param mas массив
     */
    public EvenIterator(int[] mas) {
        this.mas = mas;
        this.length = this.mas.length;
        this.nextStep();
    }

    private void nextStep() {
        while (this.hasNext() && mas[cursor] % 2 != 0) {
            cursor++;
        }
    }

    /**
     * Метод проверяет можно ли считать число, если нельзя, выбрасывает исключение
     * затем считывает число переводя корретку дальше
     * отдельный метод проверяет и проталкивает дальше если следующее число не подходит
     * до тех пор пока длина массива не закончится
     *
     * @return следующее число в массиве
     */
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        this.result = this.mas[this.cursor++];
        this.nextStep();
        return this.result;
    }

    /**
     * Определяет не закончился ли массив и есть ли следующий элемент
     * @return true элемент есть, false элемента нет
     */
    public boolean hasNext() {
        return this.cursor != this.length;
    }
}
