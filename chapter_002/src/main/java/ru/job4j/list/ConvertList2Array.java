package ru.job4j.list;

import java.util.List;

/**
 * Класс ConvertList2Array. Делает из списка двойной массив
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 01.05.2018
 */
public class ConvertList2Array {

    /**
     * Преобразует список в двумерный массив
     * метод toArray должен равномерно разбить лист на количество строк двумерного массива.
     * В методе toArray должна быть проверка - если количество элементов не кратно количеству строк -
     * оставшиеся значения в массиве заполнять нулями.
     *
     * @param list список
     * @param rows количество строк в массиве
     * @return двумерный массив с количеством строк = rows
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() / rows + (list.size() % rows != 0 ? 1 : 0);
        int[][] array = new int[rows][cells];
        int row = 0;
        int cell = 0;
        for (int arr : list) {
            if(cell == cells) {
                cell = 0;
                row++;
            }
            array[row][cell++] = arr;
        }
        return array;
    }
}