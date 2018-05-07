package ru.job4j.chessboard;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;


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

    private int position = 0;

    // public Optional<Figure[]> getFigures() {
    //      return Optional.ofNullable(figures);
    //  }


    /**
     * Добавляет фигуры.
     *
     * @param figure фигура, которую нужно добавить.
     * @return Figure копию класса.
     */
    public void add(Figure figure) {
        if (position < 32) {
            this.figures[this.position++] = figure; //Optional.ofNullable(figure).orElseThrow(()->new FigureNotFoundException());
        } else {
            System.out.println("Слишком много фигур");
        }
    }

    /**
     * Метод должен проверить:
     * - Что в заданной ячейки есть фигура. если нет. то выкинуть исключение;
     * - Если фигура есть. Проверить может ли она так двигаться. Если нет то упадет исключение;
     * - Проверить что полученный путь. не занят фигурами. Если занят выкинуть исключение;
     * - Если все отлично. Записать в ячейку новое новое положение Figure figure.copy(Cell dest).
     *
     * @param source начальная клетка.
     * @return dest конечная клетка.
     */
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean notFound = false;
        Cell[] way;
        int index = 0;
        for (Figure figure : this.figures) {
            if (Optional.ofNullable(figure).filter(Figure -> source.equals(figure.position)).isPresent()) {
                notFound = true;
                break;
            }
            index++;
        }
        if (!notFound) {
            throw new FigureNotFoundException();
        }
        way = this.figures[index].way(source, dest);
        for (Cell cell : way) {
            for (Figure figure : figures) {
                Optional.ofNullable(figure).filter(Figure -> cell.equals(figure.position)).ifPresent(t -> Figure.setException());
            }
        }
        this.figures[index] = figures[index].copy(dest);
        return true;
    }
}
