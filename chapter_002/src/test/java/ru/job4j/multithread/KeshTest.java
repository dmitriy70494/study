package ru.job4j.multithread;

import net.jcip.annotations.GuardedBy;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * тест класса Kesh.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 01.06.2018
 */
public class KeshTest {

    String result;

    volatile int index;

    @Test
    public void whenFindFileWithExtsJavaAndFindWordTest() {
        Kesh kesh = new Kesh();
        kesh.add(new Base(1, 1, "1"));
        Thread[] threads = new Thread[10];
        for (index = 0; index < threads.length; index++) {
            threads[index] = new Thread() {

                int count = index;

                @Override
                public void run() {
                    do {
                        try {
                            Base oldBase = kesh.get(1);
                            Base newBase = new Base(oldBase.getId(), oldBase.getVersion() + 1, String.valueOf(count++));
                            kesh.update(newBase);
                        } catch (OptimisticException oe) {
                            result = oe.getMessage();
                        }
                    } while (count < 3);
                }
            };
            threads[index].start();
        }

        try {
            for (int index = 0; index < threads.length; index++) {
                threads[index].join();
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        assertThat(result, is("OptimisticException"));

    }
}