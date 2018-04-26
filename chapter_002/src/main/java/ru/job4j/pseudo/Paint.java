package ru.job4j.pseudo;

/**
 * Прорисовывает переданные фигуры
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 26.04.2018
 */
public class Paint {

    /**
     * Прорисовывает переданную фигуру
     * @param  shape фигуры, которые нужно прорисовать
     */
    public static void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        draw(new Triangle());
        draw(new Square());
    }
}
