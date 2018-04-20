package ru.job4j.array;

import java.util.Arrays;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class ArrayDuplicate {
    public String[] remove(String[] array) {
        int count = 1;
        boolean bool = true;
        for (int i = 1; i < array.length; i++) {
            for(int s = 0; s < count; s++) {
                if (array[i].equals(array[s])) {
                    bool = false;
                    break;
                }
            }
            if (bool) {
                array[count] = array[i];
                count++;
            } else {
                bool = true;
            }
        }

        return Arrays.copyOf(array, count);
    }
}
