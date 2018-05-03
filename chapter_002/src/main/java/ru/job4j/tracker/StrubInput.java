package ru.job4j.tracker;

import java.util.List;

/**
 * Для работы с вводом и выводом в консоль, для эмуляции действий
 * использует interface Input.
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 24.04.2018
 */
public class StrubInput implements Input {
    private String[] answers;
    private int position;

    /**
     * Конструтор инициализирующий эмуляцию ввода.
     * @param answers содержит данные для проверки, они поочередно будут вводиться в класс
     */
    public StrubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Возвращает эмуляцию ввода в консоль от пользователя
     * @param phrase ничего не делает, так как это эмулятор ввода
     */
    public String ask(String phrase) {
        return this.answers[this.position++];
    }

    /**
     * Возвращает эмуляцию ввода в консоль от пользователя
     * @param phrase ничего не делает, так как это эмулятор ввода
     * @param
     */
    public int ask(String phrase, List<Integer> range) {
        return Integer.valueOf(this.answers[this.position++]);
    }
}
