package ru.job4j.loop;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class Board {
    /**
     * Создает строку с доской и с заданными размерами.
     * @param width ширина доски.
     * @param height высота доски.
     * @return result строка с доской с заданными размерами.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int s = 0; s < height; s++) {
            for (int i = 0; i < width; i++) {
                // условие проверки, что писать пробел или X
                // Выше в задании мы определили закономерность, когда нужно проставлять X
                if ((s + i) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод на новую строку.
            screen.append(ln);
        }
        return screen.toString();
    }
}