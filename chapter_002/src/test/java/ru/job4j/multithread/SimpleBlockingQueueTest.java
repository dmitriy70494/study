package ru.job4j.multithread;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * тест SimpleBlockingQueue.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 28.05.2018
 */
public class SimpleBlockingQueueTest {

    @Test
    public void whenTwoThreadsAddAndPollElements() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>();
        int[] except = {1, 3, 5, 7, 9, 11, 9, 7, 5, 3, 1, 0};
        int[] result = new int[12];
        Thread produser = new Thread(new Runnable() {
            int index = 0;
            @Override
            public void run() {
                while (index < except.length) {
                    queue.offer(except[index++]);
                }
            }
        });
        Thread consumer = new Thread(new Runnable() {
            int position = 0;
            @Override
            public void run() {
                while (position < 12) {
                    result[position++] = queue.poll();
                }
            }
        });
        /**Thread polledTwo = new Thread(new Runnable() {
            int position = 4;
            @Override
            public void run() {
                while (position < 12) {
                    result[position++] = queue.poll();
                }
            }
        });
        Thread polledThree = new Thread(new Runnable() {
            int position = 8;
            @Override
            public void run() {
                while (position < 12) {
                    result[position++] = queue.poll();
                }
            }
        });*/
        produser.start();
        consumer.start();
        produser.join();
        consumer.join();
        assertThat(Arrays.toString(result), is(Arrays.toString(except)));
    }
}