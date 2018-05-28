package ru.job4j.multithread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

@ThreadSafe
public class ConcurrentDynamicArray<E> implements Iterable<E> {
    /**
     * Хранит объекты
     */
    @GuardedBy("this")
    private Object[] objects;

    /**
     * Хранит номер позиции куда можно добавлять новый объект, а также олицетворяет
     */
    private volatile int size = 0;

    /**
     * Счетчик изменений
     */
    private volatile int modCount = 0;

    /**
     * Создает массив
     *
     * @param volumeArray размер массива
     */
    public ConcurrentDynamicArray(int volumeArray) {
        this.objects = new Object[volumeArray];
    }

    /**
     * Занимается поиском объекта в массиве
     *
     * @param date
     * @return
     */
    public boolean conteins(E date) {
        boolean finded = false;
        synchronized (this) {
            for (Object object : objects) {
                if (date.equals(object)) {
                    finded = true;
                }
            }
        }
        return finded;

    }

    /**
     * Добавляет объект. Увеличивает размер массива, если тот заканчивается, и увеличивает modCount
     * которая фиксирует изменения
     *
     * @param object Добавляемый объект
     */
    public void add(E object) {
        synchronized (this) {
            if (size == objects.length) {
                objects = Arrays.copyOf(objects, size + 10);
            }
            this.objects[size++] = object;
            modCount++;
        }
    }


    /**
     * Возвращает объект из ячейки
     *
     * @param index номер ячейки
     * @return Содержащийся приведенный к данному типу объектк
     */
    public E get(int index) {
        synchronized (this) {
            return (E) this.objects[index];
        }
    }

    /**
     * Возвращает итератор данного массива. Возвращает только заполненные ячейки массива.
     *
     * @return Итератор
     */
    @Override
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
                return get(index++);
            }

            public boolean hasNext() {
                return index < size;
            }
        };
    }

}
