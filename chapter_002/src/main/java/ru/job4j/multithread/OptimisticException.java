package ru.job4j.multithread;

/**
 * класс OptimisticException. Исключение.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 01.06.2018
 */
public class OptimisticException extends RuntimeException {

    public OptimisticException(String s) {
        super(s);
    }
}
