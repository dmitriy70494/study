package ru.job4j.simplearray;

import java.util.Iterator;
import java.util.Optional;

/**
 * класс AbstractStore. Реализует все одинаковые методы.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 14.05.2018
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    protected SimpleArray<T> objects;

    protected AbstractStore(int size) {
        this.objects = new SimpleArray<T>(size);
    }

    public void add(T model) {
        this.objects.add(model);
    }

    public boolean replace(String id, T model) {
        Iterator<T> it = objects.iterator();
        boolean access = false;
        int index = 0;
        while (it.hasNext()) {
            T object = it.next();
            if (id.equals(object.getId())) {
                objects.set(index, model);
                access = true;
                break;
            }
            index++;
        }
        return access;
    }

    public boolean delete(String id) {
        Iterator<T> it = objects.iterator();
        boolean access = false;
        int index = 0;
        while (it.hasNext()) {
            T object = it.next();
            if (id.equals(object.getId())) {
                objects.delete(index);
                access = true;
                break;
            }
            index++;
        }
        return access;
    }

    public T findById(String id) {
        Iterator<T> it = objects.iterator();
        T result = null;
        while (it.hasNext()) {
            T object = it.next();
            if (id.equals(object.getId())) {
                result = object;
                break;
            }
        }
        return result;
    }
}
