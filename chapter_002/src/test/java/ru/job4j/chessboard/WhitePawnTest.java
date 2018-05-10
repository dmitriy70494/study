
package ru.job4j.chessboard;

import java.util.*;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса WhitePawn. проверка работы алгоритма ходов белой пешки на 1 клетку, на 2 клетки, на 1 клетку не с 6 поля + проверка на ошибочный ход
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 10.05.2018
 */
public class WhitePawnTest {

    @Test
    public void whenWhitePawnStepsX6Y6ToX6Y5() {
        WhitePawn pawn = new WhitePawn(new Cell(6, 6));
        Cell[] resultSteps = null;
        try {
            resultSteps = pawn.way(new Cell(6, 6), new Cell(6, 5));
        } catch (ImpossibleMoveException ime) {
            System.out.println("Невозможный ход");
        }
        assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(6, 5)).toString()));
    }


    @Test
    public void whenWhitePawnStepsX6Y6ToX6Y4() {
        WhitePawn pawn = new WhitePawn(new Cell(6, 6));
        Cell[] resultSteps = null;
        try {
            resultSteps = pawn.way(new Cell(6, 6), new Cell(6, 4));
        } catch (ImpossibleMoveException ime) {
            System.out.println("Невозможный ход");
        }
        assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(6, 4)).toString()));
    }

    @Test
    public void whenWhitePawnStepsX6Y6ToX6Y3() {
        String result = "";
        WhitePawn pawn = new WhitePawn(new Cell(6, 6));
        Cell[] resultSteps = null;
        try {
            resultSteps = pawn.way(new Cell(6, 6), new Cell(6, 3));
        } catch (ImpossibleMoveException ime) {
            result = "Невозможный ход";
        }
        assertThat(result, is("Невозможный ход"));
    }

    @Test
    public void whenWhitePawnStepsX6Y5ToX6Y4() {
        WhitePawn pawn = new WhitePawn(new Cell(6, 5));
        Cell[] resultSteps = null;
        try {
            resultSteps = pawn.way(new Cell(6, 5), new Cell(6, 4));
        } catch (ImpossibleMoveException ime) {
            System.out.println("Невозможный ход");
        }
        assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(6, 4)).toString()));
    }
}