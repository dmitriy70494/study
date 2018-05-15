package ru.job4j.simplearraylist;

import java.util.*;

/**
 * класс DynamicArray. Делает обертку над массивом и динамически его увеличивает, когда тот заканчивается.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 15.05.2018
 */
public class DynamicArray<E> implements Iterable<E> {
    /**
     * Хранит объекты
     */
    private Object[] objects;

    /**
     * Хранит номер позиции куда можно добавлять новый объект, а также олицетворяет
     */
    private int size = 0;

    /**
     * Счетчик изменений
     */
    private int modCount = 0;

    /**
     * Добавляет данное значение когда массив заполнен.
     */
    private int increase = 10;

    /**
     * Создает массив
     *
     * @param volumeArray размер массива
     */
    public DynamicArray(int volumeArray) {
        this.objects = new Object[volumeArray];
    }

    /**
     * Добавляет объект. Увеличивает размер массива, если тот заканчивается, и увеличивает modCount
     * которая фиксирует изменения
     *
     * @param object Добавляемый объект
     */
    public void add(E object) {
        if (size == objects.length) {
            objects = Arrays.copyOf(objects, size + increase);
        }
        this.objects[size++] = object;
        modCount++;
    }


    /**
     * Возвращает объект из ячейки
     *
     * @param index номер ячейки
     * @return Содержащийся приведенный к данному типу объектк
     */
    public E get(int index) {
        return (E) this.objects[index];
    }

    /**
     * Возвращает итератор данного массива. Возвращает только заполненные ячейки массива.
     *
     * @return Итератор
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            int expected = modCount;
            public E next() {
                if (expected != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) objects[index++];
            }

            public boolean hasNext() {
                return index < size;
            }
        };
    }

}
