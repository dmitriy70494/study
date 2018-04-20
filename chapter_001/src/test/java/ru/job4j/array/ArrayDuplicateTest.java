package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] array = {"Папа", "Привет", "Привет", "Мир", "Привет", "Супер", "Привет", "Привет", "Мир", "Мама", "Привет", "Супер", "Привет", "Привет", "Мир", "Привет", "Супер", "g"};
        String[] resultArray = duplicate.remove(array);
        String[] expectArray = {"Папа", "Привет", "Мир", "Супер", "Мама", "g"};
        assertThat(resultArray, is(expectArray));
        //напишите здесь тест, проверяющий удаление дубликатов строк из массива строк.
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicateOneWorld() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] array = {"Папа"};
        String[] resultArray = duplicate.remove(array);
        String[] expectArray = {"Папа"};
        assertThat(resultArray, is(expectArray));
        //напишите здесь тест, проверяющий удаление дубликатов строк из массива строк.
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicateTwoWorld() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] array = {"Привет", "Привет"};
        String[] resultArray = duplicate.remove(array);
        String[] expectArray = {"Привет"};
        assertThat(resultArray, is(expectArray));
        //напишите здесь тест, проверяющий удаление дубликатов строк из массива строк.
    }
}