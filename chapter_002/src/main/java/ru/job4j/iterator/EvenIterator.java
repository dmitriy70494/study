package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class EvenIterator<E extends Integer> implements Iterator<Integer> {

    /**
     * массив
     */
    private final int[] mas;

    /**
     * Длина массива
     */
    private final int length;

    /**
     * Индекс элемента на котором находится корретка метода next
     */
    private int cursor = 0;

    /**
     * Конструктор инициализирует все необходимые параметры, сам массив и размер массива по длине
     *
     * @param mas исходный массив
     */
    public EvenIterator(int[] mas) {
        this.mas = mas;
        this.length = this.mas.length;
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
        return this.mas[this.cursor++];
    }

    /**
     * Определяет не закончился ли массив и есть ли следующий элемент
     * @return true элемент есть, false элемента нет
     */
    public boolean hasNext() {
        boolean access = this.cursor != this.length;
        while (access && mas[cursor] % 2 != 0) {
            access = ++this.cursor != this.length;
        }
        return access;
    }
}
