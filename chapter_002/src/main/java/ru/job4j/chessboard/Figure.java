package ru.job4j.chessboard;

/**
 * Абстрактный класс
 * этот класс будет описывать абстрактное поведение шахматной доски.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 27.04.2018
 */
public abstract class Figure {



    /**
     * описывает ячейки шахматной доски. Содержит координаты x, y. Cell не может содержать объект Figure.
     * Важно, что position объявлена final = изменения координаты будет происходить за счет пересоздания
     * объекта.
     *
     */
    final Cell position;

    /**
     * Конструктор, инициализирует position
     * @param position текущие координаты фигуры на доске.
     */
    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Метод должен работать так. dest - задает ячейку, куда следует пойти.
     * Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
     * Если фигура туда пойти не может. выбросить исключение ImposibleMoveException
     * @param source клетка на которой стоит фигура
     * @param dest задает ячейку, куда следует пойти.
     * @return Cell[] массив возможных ходов
     *
     */
    abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    /**
     * Он должен создавать объект Figure с координатой Cell dest.
     * Например. для класса
     * class Bishop impl Figure {
     *     Figure copy(Cell dest) {
     *         return new Bishop(dest);
     *     }
     * }
     *
     * @param dest задает ячейку, куда следует пойти.
     * @return Figure копию класса
     *
     */
    abstract Figure copy(Cell dest);

    public Cell getPosition() {
        return this.position;
    }

    public static void setException() throws OccupiedWayException {
        throw new OccupiedWayException();
    }

    }

