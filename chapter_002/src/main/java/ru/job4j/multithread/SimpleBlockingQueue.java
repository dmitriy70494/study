package ru.job4j.multithread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * класс SimpleBlockingQueue.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 28.05.2018
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    /**
     * Очередь, хранящае объекты, на нее ставится индикатор монитора
     */
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<T>();

    /**
     * Количество элементов в очереди
     */
    private volatile int size = 0;

    /**
     * Получает значение
     *
     * @param value
     */
    void offer(T value) {
        synchronized (this) {
            while (size == 3) {
                try {
                    this.wait();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                    System.out.println("Исключение метода wait");
                }
            }
            queue.offer(value);
            size++;
            this.notify();
        }
    }

    /**
     * Должен вернуть объект из внутренней коллекции. Если в коллекции объектов нет. то нужно перевести текущую нить в состояние ожидания.
     * Важный момент, когда нить переводить в состояние ожидания. то она отпускает объект монитор и другая нить тоже может выполнить этот метод.
     * Рассмотрим диаграмму выполнения этой программы.
     *
     * @return
     */
    public T poll() {
        synchronized (this) {
            while (size == 0) {
                try {
                    this.wait();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                    System.out.println("Исключение метода wait");
                }
            }
            T value = queue.poll();
            size--;
            this.notify();
            return value;
        }
    }
}