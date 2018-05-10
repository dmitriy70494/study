package ru.job4j.chessboard;


import java.util.*;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса Queen. проверка работы алгоритма ходов Королевы во все стороны по прямым + проверка на ошибочный ход
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 10.05.2018
 */
public class QueenTest {

    @Test
    public void whenQueenStepsX3Y3ToX7Y6() {
        Queen queen = new Queen(new Cell(3, 3));
        Cell[] resultSteps = null;
        try {
            resultSteps = queen.way(new Cell(3, 3), new Cell(7, 3));
        } catch (ImpossibleMoveException ime) {
        }
        assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(4, 3), new Cell(5, 3), new Cell(6, 3), new Cell(7, 3)).toString()));
    }


    @Test
    public void whenQueenStepsX3Y3ToX0Y3() {
        Queen queen = new Queen(new Cell(3, 3));
        Cell[] resultSteps = null;
        try {
            resultSteps = queen.way(new Cell(3, 3), new Cell(0, 3));
        } catch (ImpossibleMoveException ime) {
        }
        assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(2, 3), new Cell(1, 3), new Cell(0, 3)).toString()));
    }

    @Test
    public void whenQueenStepsX3Y3ToX3Y0() {
        Queen queen = new Queen(new Cell(3, 3));
        Cell[] resultSteps = null;
        try {
            resultSteps = queen.way(new Cell(3, 3), new Cell(3, 0));
        } catch (ImpossibleMoveException ime) {
        }
        assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(3, 2), new Cell(3, 1), new Cell(3, 0)).toString()));
    }

    @Test
    public void whenQueenStepsX3Y3ToX6Y6() {
        Queen queen = new Queen(new Cell(3, 3));
        Cell[] resultSteps = null;
        try {
            resultSteps = queen.way(new Cell(3, 3), new Cell(6, 6));
        } catch (ImpossibleMoveException ime) {
        }
        assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(4, 4), new Cell(5, 5), new Cell(6, 6)).toString()));
    }

    @Test
    public void whenQueenStepsX8Y0ToX7Y2Miss() {
        Queen queen = new Queen(new Cell(8, 0));
        Cell[] resultSteps = null;
        String results = null;
        try {
            resultSteps = queen.way(new Cell(8, 0), new Cell(7, 2));
        } catch (ImpossibleMoveException ime) {
            results = "ImpossibleMoveException";
        }
        assertThat(results, is("ImpossibleMoveException"));
    }
}