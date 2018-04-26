package ru.job4j.pseudo;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.After;
import org.junit.Before;

/**
 * Тесты для прорисовки в классе Paint
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 26.04.2018
 */
public class PaintTest {

    /**
     * поток для управления выводом в консоль, наверное
     */
    private final PrintStream stdout = System.out;

    /**
     * Буфер для результата
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Метод для операций в начале метода, аннотация @Before, нужно добавлять в импорт
     * Заменяем стандартный вывод на вывод в пямять для тестирования.
     */
    @Before
    public void loadOutput() {
        System.out.println("в начале метода теста");
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Метод для операций в конце метода, аннотация @After
     * возвращаем обратно стандартный вывод в консоль.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("в конце метода теста");
    }

    /**
     * Тест прорисовки в классе Paint Квадрата
     * действия выполняются аннотациями
     * выполняем действия пишушиее в консоль.
     * проверяем результат вычисления
     */
    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("++++++++\n")
                                .append("++++++++\n")
                                .append("++++++++\n")
                                .append("++++++++\n")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    /**
     * Тест прорисовки в классе Paint Треугольника
     * действия выполняются аннотациями
     * выполняем действия пишушиее в консоль.
     * проверяем результат вычисления
     */
    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("   +   \n")
                                .append("  +  +  \n")
                                .append(" +    + \n")
                                .append("++++++++\n")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
