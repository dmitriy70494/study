package ru.job4j.chessboard;

/**
 * Cell - описывает ячейки шахматной доски. Содержит координаты x, y.
 * Cell не может содержать объект Figure.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class Cell {

    /**
     * координата клетки
     */
    private final int x;

    /**
     * координата клетки
     */
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean equals(Cell obj) {
        return obj.getX() == this.x && obj.getY() == this.y;
    }

    public String toString() {
        return x + " " + y;
    }

}
