package ru.job4j.chessboard;

/**
 * Класс AbstractPawn. Определяет общий принцип хода пешки,
 * а его реализации будут просто ходить в разные стороны и при разных условиях ходить на 2 клетки вперед.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 24.04.2018
 */
public abstract class AbstractPawn extends Figure {

    protected int step;
    protected int cell;

    /**
     * Конструктор, устанавливает положение фигуры на доске
     *
     * @param dest задает ячейку положения на доске.
     */
    public AbstractPawn(Cell dest) {
        super(dest);
    }

    /**
     * В данном методе используется Композиция, вызываются поочередно два разных метода логики ладьи и слона
     * Метод должен работать так. dest - задает ячейку, куда следует пойти.
     * Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
     * Если фигура туда пойти не может. выбросить исключение ImposibleMoveException
     *
     * @param source клетка на которой стоит фигура
     * @param dest задает ячейку, куда следует пойти.
     * @return Cell[] массив возможных ходов
     */
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!(source.getY() + step == dest.getY()) && !(source.getY() == cell && source.getY() + step * 2 == dest.getY())) {
            throw new ImpossibleMoveException();
    }
        return new Cell[]{dest};
    }

    /**
     * Он должен создавать объект Figure с координатой Cell dest.
     *
     * @param dest координата фигуры на доске.
     * @return Figure копию класса
     *
     */
    abstract Figure copy(Cell dest);
}