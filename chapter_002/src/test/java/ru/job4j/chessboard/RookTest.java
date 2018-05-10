package ru.job4j.chessboard;


 import java.util.*;
 import org.junit.Test;
 import static org.hamcrest.core.Is.is;
 import static org.junit.Assert.assertThat;

 /**
  * Тесты для класса Rook. проверка работы алгоритма ходов ладьи во все стороны по прямым + проверка на ошибочный ход
  * @author Dmitriy Balandin (d89086362742@yandex.ru)
  * @version $Id$
  * @since 28.04.2018
  */
 public class RookTest {

     @Test
     public void whenRookStepsX3Y3ToX7Y6() {
         Rook rook = new Rook(new Cell(3, 3));
         Cell[] resultSteps = null;
         try {
             resultSteps = rook.way(new Cell(3, 3), new Cell(7, 3));
         } catch (ImpossibleMoveException ime) {
         }
         assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(4, 3), new Cell(5, 3), new Cell(6, 3), new Cell(7, 3)).toString()));
     }


     @Test
     public void whenRookStepsX3Y3ToX0Y3() {
         Rook rook = new Rook(new Cell(3, 3));
         Cell[] resultSteps = null;
         try {
             resultSteps = rook.way(new Cell(3, 3), new Cell(0, 3));
         } catch (ImpossibleMoveException ime) {
         }
         assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(2, 3), new Cell(1, 3), new Cell(0, 3)).toString()));
     }

     @Test
     public void whenRookStepsX3Y3ToX3Y0() {
         Rook rook = new Rook(new Cell(3, 3));
         Cell[] resultSteps = null;
         try {
             resultSteps = rook.way(new Cell(3, 3), new Cell(3, 0));
         } catch (ImpossibleMoveException ime) {
         }
         assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(3, 2), new Cell(3, 1), new Cell(3, 0)).toString()));
     }

     @Test
     public void whenRookStepsX3Y3ToX3Y6() {
         Rook rook = new Rook(new Cell(3, 3));
         Cell[] resultSteps = null;
         try {
             resultSteps = rook.way(new Cell(3, 3), new Cell(3, 6));
         } catch (ImpossibleMoveException ime) {
         }
         assertThat(Arrays.asList(resultSteps).toString(), is(Arrays.asList(new Cell(3, 4), new Cell(3, 5), new Cell(3, 6)).toString()));
     }

     @Test
     public void whenRookStepsX8Y0ToX7Y2Miss() {
         Rook rook = new Rook(new Cell(8, 0));
         Cell[] resultSteps = null;
         String results = null;
         try {
             resultSteps = rook.way(new Cell(8, 0), new Cell(7, 2));
         } catch (ImpossibleMoveException ime) {
             results = "ImpossibleMoveException";
         }


         assertThat(results, is("ImpossibleMoveException"));
     }
 }
