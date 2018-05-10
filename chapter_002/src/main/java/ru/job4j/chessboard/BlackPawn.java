package ru.job4j.chessboard;

/**
 * Класс BlackPawn. Определяет ход черной пешки строго вниз доски.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 10.05.2018
 */
public class BlackPawn  extends AbstractPawn {
    /**
     * Конструктор, устанавливает положение фигуры на доске
     *
     * @param dest задает ячейку положения на доске.
     */
    public BlackPawn(Cell dest) {
        super(dest);
        step = 1;
        cell = 1;
    }

    /**
     * Он должен создавать объект Figure с координатой Cell dest.
     *
     * @param dest координата фигуры на доске.
     * @return Figure копию класса
     *
     */
    public Figure copy(Cell dest) {
        return new BlackPawn(dest);
    }
}