package ru.job4j.multithread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {



    boolean access = false;

    List<Thread> list;

    Queue<Work> threads;

    int volume;

    int size = 0;

    public ThreadPool(int volume) {
        this.volume = volume;
        this.threads = new LinkedList<Work>();
        this.list = new ArrayList<Thread>();
        if (volume < 2) {
            throw new IllegalArgumentException();
        }
    }

    public void add(Work work) {
        threads.offer(work);
        check();
    }

    private void check() {
        if (!access) {
            access = true;
            Thread thread = new Thread() {
                public void run() {
                        while (access) {
                            System.out.println(Thread.activeCount());
                            size = Thread.activeCount();
                            while (size <= volume) {
                                Work work = threads.poll();
                                if (work == null) {
                                    break;
                                }
                                list.add(0, new Thread(work));
                                list.get(0).start();
                            }
                        }
                }
            };
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException ie) {
            }
        }
    }

    interface Work extends Runnable {

    }
}
