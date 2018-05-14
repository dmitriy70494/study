package ru.job4j.simplearray;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * класс SimpleArray. Делает универсальную обертку над массивом.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 14.05.2018
 */
public class SimpleArray<T> implements Iterable<T> {

    /**
     * Хранит объекты
     */
    private Object[] objects;

    /**
     * Хранит номер позиции куда можно добавлять новый объект
     */
    private int position = 0;

    /**
     * Создает массив
     *
     * @param size размер массива
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * Добавляет объект
     *
     * @param object Добавляемый объект
     */
    public void add(T object) {
        this.objects[position++] = object;
    }

    /**
     * Изменяет объект
     *
     * @param index индекс изменяемого объекта
     * @param model новый объект
     */
    public void set(int index, T model) {
        this.objects[index] = model;
    }

    /**
     * Удаление объекта, все соседние объекты сдвигаются на 1 ячейку
     *
     * @param index индекс удаляемого объекта
     */
    public void delete(int index) {
        for (index = index; index < position + 1; index++) {
            this.objects[index] = this.objects[index + 1];
        }
        --position;
    }

    /**
     * Возхвращает объект из ячейки
     *
     * @param index номер ячейки
     * @return Содержащийся приведенный к данному типу объектк
     */
    public T get(int index) {
        return (T) this.objects[index];
    }

    /**
     * Возвращает итератор данного массива. Возвращает только заполненные ячейки массива.
     *
     * @return Итератор
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            int length = position;
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) objects[index++];
            }

            public boolean hasNext() {
                return index < length;
            }
        };
    }
}
