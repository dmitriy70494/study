package ru.job4j.chessboard;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса Board. проверка добавления фигур на доску
 * проверка хода фигуры (перезапись фигуры с новыми координатами без преград)
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 28.04.2018
 */
public class BoardTest {

    Board board;
    String result = "все хорошо";
    Bishop bishopOne;
    /**
     * Метод для операций в начале метода, аннотация @Before, нужно добавлять в импорт
     * Заменяем стандартный вывод на вывод в пямять для тестирования.
     */
    @Before
    public void loadOutput() {
        this.board = new Board();
        this.bishopOne = new Bishop(new Cell(3, 3));
        this.board.add(bishopOne);
    }


    @Test
    public void whenBoardFiguraBishopStepsX3Y3ToX6Y6AndBishopStandsX4Y4() {
        Bishop bishopTwo = new Bishop(new Cell(4, 4));
        this.board.add(bishopTwo);
        try {
            board.move(new Cell(3, 3), new Cell(6, 6));
        } catch (FigureNotFoundException fnf) {
            this.result = "FigureNotFoundException";
        } catch (ImpossibleMoveException ime) {
            this.result = "ImpossibleMoveException";
        } catch (OccupiedWayException owe) {
            this.result = "OccupiedWayException";
        }
        assertThat(this.result, is("OccupiedWayException"));
    }

    @Test
    public void whenBoardFiguraBishopStepsX3Y3ToX6Y0() {
        Bishop bishopTwo = new Bishop(new Cell(4, 4));
        this.board.add(bishopTwo);
        try {
            this.board.move(new Cell(3, 3), new Cell(6, 0));
        } catch (FigureNotFoundException fnf) {
            this.result = "FigureNotFoundException";
        } catch (ImpossibleMoveException ime) {
            this.result = "ImpossibleMoveException";
        } catch (OccupiedWayException owe) {
            this.result = "OccupiedWayException";
        }
        assertThat(this.result, is("все хорошо"));
    }

    @Test
    public void whenBoardFiguraBishopStepsX3Y3ToX6Y0ButBishopStandsX3Y4() {
        Bishop bishopOne = new Bishop(new Cell(3, 4));
        Bishop bishopTwo = new Bishop(new Cell(4, 4));
        this.board = new Board();
        this.board.add(bishopOne);
        this.board.add(bishopTwo);
        try {
            this.board.move(new Cell(3, 3), new Cell(6, 0));
        } catch (FigureNotFoundException fnf) {
            result = "FigureNotFoundException";
        } catch (ImpossibleMoveException ime) {
            result = "ImpossibleMoveException";
        } catch (OccupiedWayException owe) {
            result = "OccupiedWayException";
        }
        assertThat(result, is("FigureNotFoundException"));
    }

    @Test
    public void whenBoardFiguraBishopStepsX3Y3ToX6Y1() {
        Bishop bishopOne = new Bishop(new Cell(3, 3));
        Bishop bishopTwo = new Bishop(new Cell(4, 4));
        Board board = new Board();
        board.add(bishopOne);
        board.add(bishopTwo);
        String result = "все хорошо";
        try {
            board.move(new Cell(3, 3), new Cell(6, 1));
        } catch (FigureNotFoundException fnf) {
            result = "FigureNotFoundException";
        } catch (ImpossibleMoveException ime) {
            result = "ImpossibleMoveException";
        } catch (OccupiedWayException owe) {
            result = "OccupiedWayException";
        }
        assertThat(result, is("ImpossibleMoveException"));
    }
}
