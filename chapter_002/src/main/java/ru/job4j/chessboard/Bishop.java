package ru.job4j.chessboard;

/**
 * Класс Bishop. Наследуется от Figure, содержит в себе логику копирования себя с новыми координатами и
 * алгоритм хода слона
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 24.04.2018
 */
public class Bishop extends Figure {

    /**
     * Конструктор, устанавливает положение фигуры на доске
     *
     * @param dest задает ячейку положения на доске.
     */
    public Bishop(Cell dest) {
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

        int cellX = dest.getX();
        int cellY = dest.getY();
        int indexX = source.getX() - dest.getX();
        int indexY = source.getY() - dest.getY();
        int stepX = 1;
        if (indexX < 0) {
            indexX *= -1;
            stepX = -1;
         }
        int stepY = (indexY < 0) ? -1 : 1;
        if (!(indexX - indexY * stepY == 0)) {
            throw new ImpossibleMoveException();
        }
        Cell[] steps = new Cell[--indexX];
         while (indexX != 0) {
             steps[--indexX] = new Cell(cellX += stepX, cellY += stepY);
         }
        return steps;
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
         return new Bishop(dest);
     }

}
