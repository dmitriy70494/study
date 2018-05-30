package ru.job4j.multithread;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * тест Lock.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 30.05.2018
 */
public class LockTest {


    /**
     * Создает 6 потоков с именами 0 1 2 3 4 5 в обратной последовательности для чистоты эксперимента
     * Потоки просто записывают при выполнении метода принт, объекта LockUse свои имена. По условию лока они должны пройти
     * блок кода соблюдая очередность.
     *
     * @throws InterruptedException
     */
    @Test
    public void whenTwoThreadsAddAndPollElements() throws InterruptedException {
        final StringBuffer result = new StringBuffer();
        Thread[] threads = new Thread[6];
        LockUse use = new LockUse();
        for (int index = 5; index >= 0; index--) {
            threads[index] = new Thread() {
                @Override
                public void run() {
                    result.append(use.print());
                }
            };
            threads[index].setName(String.valueOf(index));
            threads[index].start();
        }
        for (int index = 0; index < 6; index++) {
            threads[index].join();
        }
        assertThat(result.toString(), is("012345"));
    }
}