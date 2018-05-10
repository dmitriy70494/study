package ru.job4j.chessboard;

/**
 * Класс Rook. Наследуется от Figure, содержит в себе логику копирования себя с новыми координатами и
 * алгоритм хода ладьи.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 24.04.2018
 */
public class Rook extends Figure {

    /**
     * Конструктор, устанавливает положение фигуры на доске
     *
     * @param dest задает ячейку положения на доске.
     */
    public Rook(Cell dest) {
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
        int stepX = cellX - source.getX();
        int stepY = cellY - source.getY();
        int index = stepX + stepY;
        index *= index > 0 ? 1 : -1;
        if (stepX == 0) {
            stepY = stepY > 0 ? 1 : -1;
        } else if (stepY == 0) {
            stepX = stepX > 0 ? 1 : -1;
        } else {
            throw new ImpossibleMoveException();
        }
        Cell[] steps = new Cell[index];
        steps[--index] = dest;
        while (index != 0) {
            cellX -= stepX;
            cellY -= stepY;
            steps[--index] = new Cell(cellX, cellY);
        }
        return steps;
    }

    /**
     * Он должен создавать объект Figure с координатой Cell dest.
     *
     * @param dest координата фигуры на доске.
     * @return Figure копию класса
     *
     */
    public Figure copy(Cell dest) {
        return new Rook(dest);
    }
}