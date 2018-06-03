package ru.job4j.bomberman;

/**
 * класс Cell. Ячейка поля.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 02.06.2018
 */
public class Cell {

    /**
     * координата
     */
    private final int x;

    /**
     * координата
     */
    private final int y;

    /**
     * Хранит нул если ячейка свободна, если занята, то хранит имя потока, который ее занял первым
     */
    private volatile String own;

    /**
     * Конструктор
     * @param y
     * @param x
     */
    Cell(int y, int x) {
        this.x = x;
        this.y = y;
    }

    /**
     * проверяет является ли ячейка соседней с этой
     * @param cell
     * @return
     */
    boolean isNeighbor(Cell cell) {
        int difference = this.x - cell.x + this.y - cell.y;
        return difference == 1 || difference == -1;
    }

    /**
     * Блокирует ячейку, прописывая имя потока если ячейка пустая
     * @return
     */
    boolean lock() {
        boolean access = own == null;
        if (access) {
            own = Thread.currentThread().getName();
        }
        return own.equals(Thread.currentThread().getName());
    }

    /**
     * разблокирует ячейку, разблокировать может только поток, который ее заблокировал
     */
    void unlock() {
        if (own.equals(Thread.currentThread().getName())) {
            own = null;
        }
    }

    /**
     * проверяет есть ли ячейка в данном поле
     * @param size
     * @return
     */
    boolean exist(int size) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }
}
