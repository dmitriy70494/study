package ru.job4j.multithread;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * тест класса ParallelSearch. Посещает файл, и переходит на следующий
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 31.05.2018
 */
public class ParallelSearchTest {

    @Test
    public void whenFindFileWithExtsJavaAndFindWordTest() {
        List<String> list = new ArrayList<String>();
        list.add(".java");
        ParallelSearch search = new ParallelSearch("C:\\projects\\study", "Test", list);
        search.init();
        //assertThat(search.result().get(0).contains(".java"), is(true));
    }
}