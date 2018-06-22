package ru.job4j.multithread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.*;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * класс ParallelSearch. Двупоточный поиск файлов
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 31.05.2018
 */
@ThreadSafe
public class ParallelSearch {
    /**
     * Указывает потоку продолжать или прекращать работу по поиску файлов
     */
    private volatile boolean worked = true;

    /**
     * Ссылка на папку
     */
    private final String root;

    /**
     * Текст, который необходимо найти
     */
    private final String text;

    /**
     * список расширений файла для поиска
     */
    private final List<String> exts;

    /**
     * поток, работа котрого заканчивается последней и которого необходио дожидаться
     */
    private Thread read;

    /**
     * Очередь файлов
     */
    @GuardedBy("this")
    private final BlockingQueue<String> files = new LinkedBlockingQueue<>();

    /**
     * список с файлами содержащими нужный текст
     */
    @GuardedBy("this")
    private final ConcurrentDynamicArray<String> paths = new ConcurrentDynamicArray<>(100);


    /**
     * Инициализирует начальные переменные
     *
     * @param root
     * @param text
     * @param exts
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    /**
     * Создает и запускает 2 потока
     * первый поток пробегает по файловой системе используя FileVisitor (класс GetFiles)
     * Если файл соответствует расширению List<Srting> exts, то путь этого файла нужно добавить в очередь files. когда
     * поток завершается он передает команду прекращать работу следующего потока.
     * Второй поток берет файл из очереди и пробегает его в поиске исходного текста.
     */
    public void init() {
        Thread search = new Thread() {
            @Override
            public void run() {
                try {
                    Files.walkFileTree(new File(root).toPath(), new GetFiles(exts, files));
                    worked = false;
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        };
        search.start();
        read = new Thread() {
            @Override
            public void run() {
                try {
                    int size = 1;
                    while (worked || size != 0) {
                        String file;
                        size = files.size();
                        file = files.poll();
                        if (file != null) {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                            int liter = 0;
                            int index = 0;
                            while (liter != -1) {
                                liter = reader.read();
                                index += liter == text.charAt(index) ? 1 : -index;
                                if (index == text.length() - 1) {
                                    paths.add(file);
                                    break;
                                }
                            }
                        }
                    }
                } catch (UnsupportedEncodingException | FileNotFoundException uee) {
                    uee.printStackTrace();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        };
        read.start();
    }

    /**
     * Ждет окончания работы поиска в файлах, как только потоки перестают работать, возвращает результат
     *
     * @return
     */
    public ConcurrentDynamicArray<String> result() {
        try {
            read.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        return this.paths;
    }
}
