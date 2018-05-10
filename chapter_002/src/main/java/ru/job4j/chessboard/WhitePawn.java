package ru.job4j.chessboard;

/**
 * Класс WhitePawn. Определяет ход белой пешки строго вверх доски.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 10.05.2018
 */
public class WhitePawn  extends AbstractPawn {
    /**
     * Конструктор, устанавливает положение фигуры на доске
     * также устанавливает линию для белой пешки, с которой она может перемещаться через 2 клетки
     * + устанавливает переменную step, которая устанавливает направление хода пешки
     *
     * @param dest задает ячейку положения на доске.
     */
    public WhitePawn(Cell dest) {
        super(dest);
        step = -1;
        cell = 6;
    }

    /**
     * Он должен создавать объект Figure с координатой Cell dest.
     *
     * @param dest координата фигуры на доске.
     * @return Figure копию класса
     *
     */
    public Figure copy(Cell dest) {
        return new WhitePawn(dest);
    }
}
