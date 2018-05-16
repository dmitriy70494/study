package ru.job4j.simpleset;

/**
 * класс SimpleHashTableSet. Создает список Set на основе массива с вычисляющимся индексом на основе хештаблицы
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 16.05.2018
 */
public class SimpleHashTableSet<T> {

    /**
     * Массив объектов
     */
    private Object[] objects;

    /**
     * размер seta
     */
    private int size = 0;

    /**
     * Изначальный размер массива
     */
    private int valueMas = 16;

    /**
     * Заполненность массива при которой его стоит увеличить в размере
     */
    private double fullness = 0.75;

    /**
     * Конструктор создает Массив размером 16 элементов
     */
    public SimpleHashTableSet() {
        this.objects = new Object[valueMas];
    }

    /**
     * Определяет индекс массива для данного объекта
     *
     * @param date
     * @return
     */
    private int hash(T date) {
        return date.hashCode() % this.valueMas + 1;
    }

    /**
     * Добавляет объект в коллекцию
     * ищет содержит ли коллекция объект в таком индексе, если не содержит, то
     * получаем отрицательное число, перевернув которое находим нужный индекс
     *
     * @param date
     * @return
     */
    private boolean addElement(T date) {
        int hash = this.find(date);
        if (hash < 0) {
            objects[-hash - 1] = date;
            size++;
        }
        return hash < 0;
    }

    /**
     * Расширяет коллекцию в 2 раза если она заполнена на 75%
     */
    private void addValueMas() {
        if (this.size >= (int) this.valueMas * this.fullness) {
            Object[] old = this.objects;
            this.valueMas = this.valueMas << 1;
            this.objects = new Object[this.valueMas];
            this.size = 0;
            for (Object date : old) {
                this.addElement((T) date);
            }
        }
    }

    /**
     * Возвращает индекс совпавшего объекта, если объекта нет и поиск наткнулся на null в ячейке, возвращает ее отрицательное значение
     * 0 используется его относительность и к положительному и отрицательному нигелируется -1 при значении null. при добавлении объектов 1 учитывается
     * @param date
     * @return
     */
    private int find(T date) {
        int finded;
        int hash = this.hash(date);
        while (true) {
            if (objects[hash] == null) {
                finded = -hash - 1;
                break;
            }
            if (date.equals(objects[hash])) {
                finded = hash;
                break;
            }
            hash = ++hash < valueMas ? hash : 1;
        }
        return finded;
    }

    /**
     * Возвращает true, если объект добавился в коллекцию, если он на этот момент уже там содержался
     * возвращает false и не добавляет его
     * @param date
     * @return
     */
    public boolean add(T date) {
        this.addValueMas();
        return this.addElement(date);
    }

    /**
     * проверяет содержится ли объект в контейнере
     * @param date
     * @return
     */
    public boolean conteins(T date) {
        return this.find(date) >= 0;
    }

    /**
     * удаляет данный объект из массива, если он в нем находится
     * @param date
     * @return
     */
    public boolean remove(T date) {
        int position = this.find(date);
        if (position >= 0) {
            objects[position] = null;
            size--;
        }
        return position >= 0;
    }
}
