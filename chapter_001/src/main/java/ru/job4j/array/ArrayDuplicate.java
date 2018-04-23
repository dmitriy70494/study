package ru.job4j.array;

import java.util.Arrays;

/**
 * Удаляет из массива повторяющиеся слова
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class ArrayDuplicate {
    /**
     * первое слово уникально изначально, каждое следующее слово проверяется на уникальность с теми,
     * которые стоят первыми. Если слово не уникально, то check меняется на false и программа проверяет
     * следующее слово. Уникальные слова записываются по адресу count.
     * @author Dmitriy Balandin (d89086362742@yandex.ru)
     * @param array исходный массив
     * @return обрезанный массив
     * @since 20.04.2018
     */
    public String[] remove(String[] array) {
        int count = 1;
        boolean check = true;
        for (int i = 1; i < array.length; i++) {
            for (int s = 0; s < count; s++) {
                if (array[i].equals(array[s])) {
                    check = false;
                    break;
                }
            }
            if (check) {
                array[count] = array[i];
                count++;
            } else {
                check = true;
            }
        }

        return Arrays.copyOf(array, count);
    }
}
