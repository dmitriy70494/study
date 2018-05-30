package ru.job4j.multithread;

/**
 * класс LockUse. Для проверки Lock.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 30.05.2018
 */
public class LockUse {

    private Lock lock = new Lock();

    public String print() {
        String result;
        lock.lock();
        result = Thread.currentThread().getName();
        lock.unlock();
        return result;
    }
}
