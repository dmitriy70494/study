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
        Set<Integer> set = wi.getIndexes4Word("Се");
        for (Integer index : set) {
            System.out.println();
        }
        //assertThat(set.contains(22), is(true));
    }

    @Test
    public void hasNextSequentialInvocation() {
        WordIndex wi = new WordIndex();
        wi.loadFile("test.txt");
        Set<Integer> set = wi.getIndexes4Word("и");
        //assertThat(set.toString(), is(Arrays.asList(4620, 1293, 2317, 1678, 6159, 3092, 4757, 5018, 6556, 6048, 161, 1697, 3105, 2340, 1317, 1573, 3495, 2731, 4651, 6443, 430, 6318, 4658, 819, 2617, 3385, 4672, 2625, 6209, 4808, 5196, 3151, 5457, 3666, 3932, 5983, 1890, 5346, 2791, 237, 5742, 5492, 1653, 122, 2428, 3196, 2813, 6526, 1919).toString()));
    }
}