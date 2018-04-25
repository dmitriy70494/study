package ru.job4j.tracker;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class ConsoleInput {

    /**
     * поток.
     */
    BufferedReader reader;

    /**
     * Конструтор инициализирующий поток BufferedReader.
     */
    public ConsoleInput() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Отображает вопрос пользователю и ждет ответ от пользователя
     * @param phrase Выводит в консоль сообщение от пользователя
     */
    public String ask(String phrase) {
        try {
            System.out.println(phrase);
            return reader.readLine();
        } catch(IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
        return "Ошибка";
    }
}
