package ru.job4j.chessboard;

/**
 * Класс Knight. Наследуется от Figure, содержит в себе логику копирования себя с новыми координатами и
 * алгоритм хода коня
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 24.04.2018
 */
public class Knight extends Figure {

    /**
     * Конструктор, устанавливает положение фигуры на доске
     *
     * @param dest задает ячейку положения на доске.
     */
    public Knight(Cell dest) {
        super(dest);
    }

    /**
     * Метод должен работать так. dest - задает ячейку, куда следует пойти.
     * Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
     * Если фигура туда пойти не может. выбросить исключение ImposibleMoveException
     * @param source клетка на которой стоит фигура
     * @param dest задает ячейку, куда следует пойти.
     * @return Cell[] массив возможных ходов
     */
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        int indexX = source.getX() - dest.getX();
        int indexY = source.getY() - dest.getY();
        indexX *= indexX < 0 ? -1 : 1;
        indexY *= indexY < 0 ? -1 : 1;
        indexX += indexX < indexY ? 1 : -1;
        if (indexX != indexY) {
            throw new ImpossibleMoveException();
        }
        return new Cell[]{dest};
    }

    /**
     * Он должен создавать объект Figure с координатой Cell dest.
     * Например. для класса
     * class Bishop impl Figure {
     *     Figure copy(Cell dest) {
     *         return new Bishop(dest);
     *     }
     * }
     *
     * @param dest координата фигуры на доске.
     * @return Figure копию класса
     *
     */
    public Figure copy(Cell dest) {
        return new Knight(dest);
    }

}