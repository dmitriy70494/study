package ru.job4j.chessboard;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса Bishop. проверка работы алгоритма ходов слона по диагоналям
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 28.04.2018
 */
public class BishopTest {

    @Test
    public void whenBishopStepsX3Y3ToX6Y6() {
        Bishop bishop = new Bishop(new Cell(3, 3));
        Cell[] resultSteps = null;
        String results;
        try {
            resultSteps = bishop.way(new Cell(3, 3), new Cell(6, 6));
        } catch (ImpossibleMoveException ime) {
            results = "ImpossibleMoveException";
        }
        Cell[] exceptSteps = {new Cell(4, 4), new Cell(5, 5)};
        boolean result = true;
        for (int index = 0; index < resultSteps.length; index++) {
            if (!resultSteps[index].equals(exceptSteps[index])) {
                result = false;
            }
        }
        assertThat(result, is(true));
    }

    @Test
    public void whenBishopStepsX0Y0ToX8Y8() {
        Bishop bishop = new Bishop(new Cell(3, 3));
        Cell[] resultSteps = null;
        String results;
        try {
            resultSteps = bishop.way(new Cell(0, 0), new Cell(8, 8));
        } catch (ImpossibleMoveException ime) {
            results = "ImpossibleMoveException";
        }
        Cell[] exceptSteps = {new Cell(1, 1), new Cell(2, 2), new Cell(3, 3), new Cell(4, 4), new Cell(5, 5), new Cell(6, 6), new Cell(7, 7)};
        boolean result = true;
        for (int index = 0; index < resultSteps.length; index++) {
            if (!resultSteps[index].equals(exceptSteps[index])) {
                result = false;
            }
        }
        assertThat(result, is(true));
    }

    @Test
    public void whenBishopStepsX8Y8ToX0Y0() {
        Bishop bishop = new Bishop(new Cell(3, 3));
        Cell[] resultSteps = null;
        String results;
        try {
            resultSteps = bishop.way(new Cell(8, 8), new Cell(0, 0));
        } catch (ImpossibleMoveException ime) {
            results = "ImpossibleMoveException";
        }
        Cell[] exceptSteps = {new Cell(7, 7), new Cell(6, 6), new Cell(5, 5), new Cell(4, 4), new Cell(3, 3), new Cell(2, 2), new Cell(1, 1)};
        boolean result = true;
        for (int index = 0; index < resultSteps.length; index++) {
            if (!resultSteps[index].equals(exceptSteps[index])) {
                result = false;
            }
        }
        assertThat(result, is(true));
    }

    @Test
    public void whenBishopStepsX8Y0ToX0Y8() {
        Bishop bishop = new Bishop(new Cell(3, 3));
        Cell[] resultSteps = null;
        String results;
        try {
            resultSteps = bishop.way(new Cell(8, 0), new Cell(0, 8));
        } catch (ImpossibleMoveException ime) {
            results = "ImpossibleMoveException";
        }
        Cell[] exceptSteps = {new Cell(7, 1), new Cell(6, 2), new Cell(5, 3), new Cell(4, 4), new Cell(3, 5), new Cell(2, 6), new Cell(1, 7)};
        boolean result = true;
        for (int index = 0; index < resultSteps.length; index++) {
            if (!resultSteps[index].equals(exceptSteps[index])) {
                result = false;
            }
        }
        assertThat(result, is(true));
    }

    @Test
    public void whenBishopStepsX8Y0ToX1Y8Miss() {
        Bishop bishop = new Bishop(new Cell(3, 3));
        Cell[] resultSteps = null;
        String results = null;
        try {
            resultSteps = bishop.way(new Cell(8, 0), new Cell(1, 8));
        } catch (ImpossibleMoveException ime) {
            results = "ImpossibleMoveException";
        }


        assertThat(results, is("ImpossibleMoveException"));
    }
}
