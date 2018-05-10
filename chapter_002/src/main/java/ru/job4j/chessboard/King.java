package ru.job4j.chessboard;

/**
 * Класс King. Наследуется от Figure, содержит в себе логику копирования себя с новыми координатами и
 * алгоритм хода Короля
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 10.05.2018
 */
public class King extends Figure {

    /**
     * Конструктор, устанавливает положение фигуры на доске
     *
     * @param dest задает ячейку положения на доске.
     */
    public King(Cell dest) {
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
        int index = source.getX() - dest.getX() + source.getY() - dest.getY();
        if (!(index == 1 || index == -1 || (source.getX() - dest.getX() == -1 || source.getX() - dest.getX() == 1 && index == 0))) {
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
        return new King(dest);
    }

}