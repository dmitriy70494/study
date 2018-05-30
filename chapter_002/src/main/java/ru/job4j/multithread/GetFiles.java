package ru.job4j.multithread;

import net.jcip.annotations.GuardedBy;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import static java.nio.file.FileVisitResult.*;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * класс GetFiles. Посещает файл, и переходит на следующий
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 31.05.2018
 */
public class GetFiles extends SimpleFileVisitor<Path> {

    /**
     * ссылка на список ограничений для файлов
     */
    private final List<String> exts;

    /**
     * ссылка на очередь с файлами для добавления файлов, если выполняется условие
     */
    @GuardedBy("files")
    private final Queue<String> files;

    /**
     * Конструктор
     * @param exts
     * @param files
     */
    public GetFiles(List<String> exts, Queue<String> files) {
        this.exts = exts;
        this.files = files;
    }

    /**
     * посещает файл(только файл, прохождение директорий ничего не делает) и если его расширение содержится в списке
     * то добавляет данный файл в очередь. Необходимо помнить что шаблон рассчитан на точное расширение без * звездочек
     * @param file
     * @param attr
     * @return
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        String result = file.toString();
        for (String exts : this.exts) {
            if (result.contains(exts)) {
                synchronized (files) {
                    files.offer(result);
                }
                break;
            }
        }
        return CONTINUE;
    }

}
