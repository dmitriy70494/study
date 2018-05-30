package ru.job4j.multithread;

/**
 * класс Lock. Выставляет блокировку на объект.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 30.05.2018
 */
public class Lock {

    private volatile int count = 0;

    /**
     * Собирает все потоки и пропускает их только при условии совпадении имени, если имя потока не совпадает, то он будет ждать своей очереди
     * Если совпадает, то будет выполнять блок кода нужный поток. Эту схему с условием и очередностью можно использовать при прочтении файлов и многого другого
     */
    public void lock() {
        try {
            while (true) {
                if (Integer.valueOf(Thread.currentThread().getName()) == count) {
                    break;
                } else {
                    Thread.currentThread().sleep(10);
                }
            }
        } catch (InterruptedException ie) {
            System.out.println("Interrapt Lock");
        }
    }

    /**
     * Изменяет условие для прохождения следующего потока
     */
    public void unlock() {
        count++;
    }
}
