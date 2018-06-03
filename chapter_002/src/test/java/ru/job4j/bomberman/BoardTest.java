package ru.job4j.bomberman;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * тест класса Board.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 03.06.2018
 */
public class BoardTest {

    /**
     * проверяет выбросил ли поток исключение
     */
    boolean exception;

    @Test(expected = NoMovesException.class)
    public void whenStartTwoThread() {
        Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable ex) {
                exception = true;
            }
        };
        Board board = new Board(3);
        Thread[] threads = board.init();
        boolean block = true;
        int[] step = {0, 0, 1, 1, 2, 2, 2, 0, 0, 2};
        int count = 0;
        while (count < 10) {
            block = board.block(step[count], step[count + 1]);
            if (block) {
                count += 2;
            }
        }
        threads[0].setUncaughtExceptionHandler(h);
        threads[1].setUncaughtExceptionHandler(h);
        try {
            threads[0].join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        if (exception) {
            throw new NoMovesException();
        }
    }

}