package ru.job4j.chessboard;

/**
 * Класс Rook. В данном классе используется композиция. Класс Королевы содержит в себе 2 класса ладьи и слона.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 24.04.2018
 */
public class Queen extends Figure {

    private Rook rook;
    private Bishop bishop;

    /**
     * Конструктор, устанавливает положение фигуры на доске
     *
     * @param dest задает ячейку положения на доске.
     */
    public Queen(Cell dest) {
        super(dest);
        rook = new Rook(dest);
        bishop = new Bishop(dest);
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
        Cell[] steps;
        if (source.getX() - dest.getX() == 0 || source.getY() - dest.getY() == 0) {
            steps = rook.way(source, dest);
        } else {
            steps = bishop.way(source, dest);
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