package ru.job4j.chessboard;

import java.util.*;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса BlackPawn. проверка работы алгоритма ходов белой пешки на 1 клетку, на 2 клетки, на 1 клетку не с 6 поля + проверка на ошибочный ход
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 10.05.2018
 */
public class BlackPawnTest {

    @Test
    public void whenBlackPawnStepsX1Y1ToX1Y2() {
        BlackPawn pawn = new BlackPawn(new Cell(1, 1));
        Cell[] resultSteps = null;
        try {
            resultSteps = pawn.way(new Cell(1, 1), new Cell(1, 2));
        } catch (ImpossibleMoveException ime) {
            System.out.println("Невозможный ход");
        }
        assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(1, 2)).toString()));
    }


    @Test
    public void whenBlackPawnStepsX6Y6ToX6Y4() {
        BlackPawn pawn = new BlackPawn(new Cell(1, 1));
        Cell[] resultSteps = null;
        try {
            resultSteps = pawn.way(new Cell(1, 1), new Cell(1, 3));
        } catch (ImpossibleMoveException ime) {
            System.out.println("Невозможный ход");
        }
        assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(1, 3)).toString()));
    }

    @Test
    public void whenBlackPawnStepsX6Y6ToX6Y3() {
        String result = "";
        BlackPawn pawn = new BlackPawn(new Cell(1, 1));
        Cell[] resultSteps = null;
        try {
            resultSteps = pawn.way(new Cell(1, 1), new Cell(1, 4));
        } catch (ImpossibleMoveException ime) {
            result = "Невозможный ход";
        }
        assertThat(result, is("Невозможный ход"));
    }

    @Test
    public void whenBlackPawnStepsX6Y5ToX6Y4() {
        BlackPawn pawn = new BlackPawn(new Cell(1, 2));
        Cell[] resultSteps = null;
        try {
            resultSteps = pawn.way(new Cell(1, 2), new Cell(1, 3));
        } catch (ImpossibleMoveException ime) {
            System.out.println("Невозможный ход");
        }
        assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(1, 3)).toString()));
    }
}