package ru.job4j.simplemap;

/**
 * класс SimpleMap. Создает список Map на основе массива с вычисляющимся индексом на основе хешa
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 17.05.2018
 */
public class SimpleMap<K, V> {

    /**
     * Массив объектов Node<Key, Value>
     */
    private Node<K, V>[] nodes;

    /**
     * Количество элементов в коллекции
     */
    private int size = 0;

    /**
     * Размер Коллекции
     */
    private int valueMas = 16;

    /**
     * Показывает при какой заполненности массива нужнно его увеличивать
     */
    private float loadFactor = 0.75f;

    /**
     * Конструктор создает Массив размером 16 элементов
     */
    public SimpleMap() {
        this.nodes = new Node[valueMas];
    }

    /**
     * Определяет индекс массива для данного объекта исходя из его хешфункции
     *
     * @param key
     * @return
     */
    private int hash(K key) {
        return key.hashCode() % this.valueMas;
    }

    /**
     * Добавляет объект в коллекцию
     * ищет содержит ли коллекция объект в таком индексе, если не содержит, то
     * получаем отрицательное число, перевернув которое находим нужный индекс
     *
     * @param key
     * @param value
     * @return
     */
    private boolean addElement(K key, V value) {
        int hash = this.find(key);
        if (hash < 0) {
            nodes[-hash - 1] = new Node<K, V>(key, value);
            size++;
        }
        return hash < 0;
    }

    /**
     * Расширяет коллекцию в 2 раза если она заполнена на 75%
     */
    private void addValueMas() {
        if (this.size >= (int) this.valueMas * this.loadFactor) {
            Node<K, V>[] old = this.nodes;
            this.valueMas = this.valueMas << 1;
            this.nodes = new Node[this.valueMas];
            this.size = 0;
            for (Node<K, V> date : old) {
                this.addElement(date.key, date.value);
            }
        }
    }

    /**
     * Возвращает индекс совпавшего объекта, если объекта нет и поиск наткнулся на null в ячейке, возвращает ее отрицательное значение
     *
     * @param key
     * @return
     */
    private int find(K key) {
        int find = this.hash(key);
        if (nodes[find] == null) {
            find = -find - 1;
        }
        return find;
    }

    /**
     * Возвращает true, если объект добавился в коллекцию, если он на этот момент уже там содержался
     * возвращает false и не добавляет его
     *
     * @param key
     * @param value
     * @return
     */
    public boolean insert(K key, V value) {
        this.addValueMas();
        return this.addElement(key, value);
    }

    /**
     * проверяет содержится ли объект с таким ключом в контейнере
     *
     * @param key
     * @return
     */
    public V get(K key) {
        int position = this.find(key);
        return position >= 0 ? this.nodes[position].value : null;
    }

    /**
     * удаляет объект с таким ключом из массива, если он в нем находится true - удалил false - нет такого объекта
     *
     * @param key
     * @return
     */
    public boolean delete(K key) {
        int position = this.find(key);
        if (position >= 0) {
            nodes[position] = null;
            size--;
        }
        return position >= 0;
    }

    /**
     * Класс предназначен для хранения ключа и значения в массиве.
     */
    private static class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
