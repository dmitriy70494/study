package ru.job4j.multithread;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * тест ThreadPool.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 30.05.2018
 */
public class ThreadPoolTest {

    private volatile int index;

    /**
     * Данный тест при 2 потоках 250мс (это происходит видимо потому, что основной поток создает еще один поток,
     * они видимо задействуют 2 ядра процессора, остается для обработки еще только два процессора время работы которых
     * и переключается между этими 2 потоками. Видимо виртуальные ядра не участвуют в обработке данных.)
     * при 8 потоках выдает около 600 мс, если запустить 100 потоков в конструкторе, то программа справляется
     * с задачей за 1 с 140 мс что в два раза выше за счет экономиии на переключении между потоками. Из результата видно,
     * что более долгие потоки выполняются последними, быстрые четные потоки первыми, номера потоков скачут из-за
     * асинхронности
     * @throws InterruptedException
     */
    @Test
    public void whenManyThreadsAddThreadPoolAssimetric() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        ThreadPool pool = new ThreadPool(2);
        for (index = 0; index < 100; index++) {
            service.execute(new Runnable() {

                volatile int thread = index;

                public void run() {
                    System.out.println("Thread: " + thread + " начал работу");
                    if (thread % 2 == 0) {
                        int result = 1;
                        for (int index = 0; index < 100; index++) {
                            result *= index;
                            result = index * result + index;
                            result = (int) Math.sqrt(result);
                        }
                    }
                    System.out.println("Thread: " + thread + " закончил работу");
                }
            });
        }
        pool.close();
    }

    /**
     * Если мы поставим 100 потоков, задача выполняется за 120 мс, а если 8 то за 650 мс, экономии за счет переключений
     * не происходит за счет того, что потоки легковесные и дополнительное время тратится на алгоритмы получения работы для потока.
     *
     * @throws InterruptedException
     */
    @Test
    public void whenManyThreadsAddThreadPool() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5 );
        for (index = 0; index < 100; index++) {
            service.submit(new Runnable() {

                volatile int thread = index;

                @Override
                public void run() {
                    for (long i = 0; i < 2000000000; i++) {

                    }
                    System.out.println("Thread: " + thread + " закончил работу");
                }
            });
        }

        System.out.println( service.isTerminated());
    }
}