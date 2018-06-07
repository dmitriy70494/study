package ru.job4j.trie;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для класса WorldIndex.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 24.05.2018
 */
public class WordIndexTest {


    @Test
    public void hasNextNextSequentialInvocation() {
        WordIndex wi = new WordIndex();
        wi.loadFile("test.txt");
        Set<Integer> set = wi.getIndexes4Word("годня");
        for (Integer index : set) {
            System.out.println(index);
        }
        //assertThat(set.contains(22), is(true));
    }

    @Test
    public void hasNextSequentialInvocation() {
        WordIndex wi = new WordIndex();
        wi.loadFile("test.txt");
        Set<Integer> set = wi.getIndexes4Word("и");
        int index = 0;
        for (Integer ind : set) {
            index++;
        }
        System.out.println(index);
    }

    @Test
    public void hasNextSequentialInvocationIndexOf() {
        WordIndex wi = new WordIndex();
        wi.loadFile("test.txt");
        Set<Integer> set = wi.getIndexes4Word("хабр");
        System.out.println("Здравствуй, Хабрахабр.".indexOf("хабр"));
        int index = 0;
        for (Integer ind : set) {
            System.out.println(ind);
        }
    }
}