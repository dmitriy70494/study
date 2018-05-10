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
public class KingTest {

    @Test
    public void whenKingStepsX3Y3ToX3Y4() {
        King king = new King(new Cell(3, 3));
        Cell[] resultSteps = null;
        try {
            resultSteps = king.way(new Cell(3, 3), new Cell(3, 4));
        } catch (ImpossibleMoveException ime) {
        }
        assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(3, 4)).toString()));
    }

    @Test
    public void whenKingStepsX3Y3ToX4Y4() {
        King king = new King(new Cell(3, 3));
        Cell[] resultSteps = null;
        try {
            resultSteps = king.way(new Cell(3, 3), new Cell(4, 4));
        } catch (ImpossibleMoveException ime) {
        }
        assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(4, 4)).toString()));
    }

    @Test
    public void whenKingStepsX3Y3ToX4Y3() {
        King king = new King(new Cell(3, 3));
        Cell[] resultSteps = null;
        try {
            resultSteps = king.way(new Cell(3, 3), new Cell(4, 3));
        } catch (ImpossibleMoveException ime) {
        }
        assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(4, 3)).toString()));
    }

    @Test
    public void whenKingStepsX3Y3ToX4Y2() {
        King king = new King(new Cell(3, 3));
        Cell[] resultSteps = null;
        try {
            resultSteps = king.way(new Cell(3, 3), new Cell(4, 2));
        } catch (ImpossibleMoveException ime) {
        }
        assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(4, 2)).toString()));
    }


    @Test
    public void whenQueenStepsX8Y0ToX7Y2Miss() {
        King king = new King(new Cell(7, 0));
        Cell[] resultSteps = null;
        String results = null;
        try {
            resultSteps = king.way(new Cell(7, 0), new Cell(1, 1));
        } catch (ImpossibleMoveException ime) {
            results = "ImpossibleMoveException";
        }
        assertThat(results, is("ImpossibleMoveException"));
    }

    @Test
    public void whenQueenStepsX3Y3ToX5Y1Miss() {
        King king = new King(new Cell(3, 3));
        Cell[] resultSteps = null;
        String results = null;
        try {
            resultSteps = king.way(new Cell(3, 3), new Cell(5, 1));
        } catch (ImpossibleMoveException ime) {
            results = "ImpossibleMoveException";
        }
        assertThat(results, is("ImpossibleMoveException"));
    }
}