package ru.job4j.multithread;

import net.jcip.annotations.GuardedBy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {

    boolean worked = true;

    private volatile int active;

    @GuardedBy("works")
    private final Queue<Runnable> works;

    public ThreadPool(int volume) {
        works = new LinkedList<Runnable>();
        for (int index = 0; index < volume; index++) {
            new Thread(new Work()).start();
        }
        active = volume - 1;
    }

    public void add(Runnable work) {
        synchronized (works) {
            works.offer(work);
        }
    }

    public void close() throws InterruptedException {
        worked = false;
        if (active != 0) {
            Thread.sleep(100);
        }
    }


    class Work implements Runnable {

        @Override
        public void run() {
            synchronized (works) {
                while (worked || works.size() != 0) {
                    Runnable nextWork = works.poll();
                    if (nextWork != null) {
                        nextWork.run();
                    }
                }
            }
            active--;
        }
    }
}


