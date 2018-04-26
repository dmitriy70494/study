package ru.job4j.pseudo;

/**
 * Класс квадрата
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 26.04.2018
 */
public class Square implements Shape {

    /**
     * Прорисовывает квадрат
     * @return String прорисовку фигуры
     */
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("++++++++\n");
        pic.append("++++++++\n");
        pic.append("++++++++\n");
        pic.append("++++++++\n");
        return pic.toString();
    }
}
