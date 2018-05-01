package ru.job4j.chessboard;

/**
 * Содержит в себе информацию о всех фигурах на доске
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 28.04.2018
 */
public class Board {

    /**
     * содержит фигуры. Количество фигур.
     */
    private Figure[] figures = new Figure[32];
    int position;

    /**
     * Добавляет фигуры.
     *
     * @param figure фигура, которую нужно добавить.
     * @return Figure копию класса.
     *
     */
    public void add(Figure figure) {
        if (position < 32) {
            this.figures[this.position++] = figure;
        } else {
            System.out.println("Слишком много фигур");
        }
    }

    /**
     *  Метод должен проверить:
     *    - Что в заданной ячейки есть фигура. если нет. то выкинуть исключение;
     *    - Если фигура есть. Проверить может ли она так двигаться. Если нет то упадет исключение;
     *    - Проверить что полученный путь. не занят фигурами. Если занят выкинуть исключение;
     *    - Если все отлично. Записать в ячейку новое новое положение Figure figure.copy(Cell dest).
     *
     * @param source начальная клетка.
     * @return dest конечная клетка.
     *
     */
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean notFound = false;
        boolean occupied = false;
        Figure movedFigure = null;
        Cell[] way;
        int index;
        for (index = 0; index < figures.length; index++) {
            if (figures[index] != null && source.equals(figures[index].position)) {
                movedFigure = figures[index];
                notFound = true;
                break;
            }
        }
        if (!notFound) {
            throw new FigureNotFoundException();
        }
            way = movedFigure.way(source, dest);
        for (Cell cell : way) {
            for (Figure figure : figures) {
                if (figure != null && cell.equals(figure.position)) {
                    occupied = true;
                }
            }
        }
        if (occupied) {
            throw new OccupiedWayException();
        }
        this.figures[index] = movedFigure.copy(dest);
        return true;
    }
}
