package ru.job4j.multithread;

import org.junit.Test;

import java.util.Iterator;


public class ProblemThreadTest {

    /**
     * Результат данного кода различный, но он меньше 1000 как ожидалось. Все происходит по той причине, что
     * данные инкрементации записываются в кэше каждого потока, а потом возвращаются из кэша обратно, и возникает ситуация,
     * что один поток фактически уже изменил значение переменной но пока не успел из своего кэша записать новое значение другой поток
     * уже взял  старое значение переменной.
     *
     * На моей машине результат получился около 933 вместо 1000.
     */
    @Test
    public void whenTwoThreadCountX() {
        /**ProblemThread problem = new ProblemThread();
        for (int index = 0; index < 10; index++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int index = 0; index < 100; index++) {
                        System.out.println(problem.count++);
                        try {
                            Thread.currentThread().sleep(1);

                        } catch (InterruptedException ie) {

                        }
                    }
                }
            }).start();
        }
        while (true) {
            int s = 0;
        }
         */
    }

    /**
     * Данный код иногда выдает нечетные числа, это связано с тем, что когда два потока используют одну переменную
     * в одном потоке может быть выполнено условие if, но когда поток "уснет", другой поток может изменить этот объект,
     * но другому потоку будет неизвестно об этом, так как у него свой отдельный стэк и проверка уже осуществилась, но
     * на печать он выводит именно фактическое значение переменной.
     *
     * результат
     * 6943
     * 6813
     * 6619 и так далее
     */
    @Test
    public void whenTwoThreadBeRaceCondition() {
        /** ProblemThread problem = new ProblemThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 1000; index++) {
                    if (problem.value % 2 == 0) {
                        System.out.println(problem.value);
                    }
                    try {
                        Thread.currentThread().sleep(10);
                    } catch (InterruptedException ie) {

                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 10000; index++) {
                    problem.value++;
                    try {
                        Thread.currentThread().sleep(1);
                    } catch (InterruptedException ie) {

                    }
                }
            }
        }).start();
        while (true) {
         int s = 0;
         }
         */
    }
}