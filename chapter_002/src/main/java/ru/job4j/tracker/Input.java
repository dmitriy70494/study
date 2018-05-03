package ru.job4j.tracker;

import java.util.List;

/**
 * Для работы с вводом и выводом в консоль пользователя или для эмуляции действий пользователя
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 24.04.2018
 */
public interface Input {

    /**
     * Отображает вопрос пользователю и ждет ответ от пользователя
     * @param phrase Выводит в консоль сообщение от пользователя
     */
    String ask(String phrase);

    int ask(String phrase, List<Integer> range);
}
