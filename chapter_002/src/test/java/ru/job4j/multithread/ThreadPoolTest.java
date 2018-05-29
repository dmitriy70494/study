package ru.job4j.multithread;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadPoolTest {

    volatile int index;

    @Test
    public void whenManyThreadsAddThreadPool() {
        ThreadPool pool = new ThreadPool(4);
        for (index = 0; index < 100; index++) {
            pool.add(new Runnable() {
                public void run() {
                    System.out.println("Thread: " + index + " начал работу");


                    //for (int index = 0; index < 10; index++) {
                      //  try {
                      //      Thread.currentThread().sleep(2);
                     //   } catch (InterruptedException ie) {
                    //    }
                  //  }
                    System.out.println("Thread: " + index + " закончил работу");
                }
            });
        }
        try {
            pool.close();
        } catch (InterruptedException ie) {
            System.out.println("Interrapted");
        }
    }

    @Test
    public void whenManyThreadsAddThreadPoolAssimetric() throws InterruptedException {
        ThreadPool pool = new ThreadPool(3);
        for (index = 0; index < 100; index++) {
            pool.add(new Runnable() {
                int thread = index;
                @Override
                public void run() {
                    System.out.println("Thread: " + thread + " начал работу");

                    if(index % 2 == 0) {
                        for (int index = 0; index < 9; index++) {
                            try {
                                Thread.currentThread().sleep(2);

                            } catch (InterruptedException ie) {

                            }
                        }
                    }
                    System.out.println("Thread: " + thread + " закончил работу");
                }
            });
        }
       while(true) {

       }
    }
}