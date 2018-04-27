package ru.job4j.tracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class ConsoleInput implements Input {

    /**
     * Ответ пользователя
     */
    String answer;

    /**
     * Поток.
     */
    private BufferedReader reader;

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
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
        return "Ошибка";
    }

    /**
     * Отображает вопрос пользователю и ждет ответ от пользователя, также проверяет ответ пользователя
     * перегруженный метод
     * @param phrase Выводит в консоль сообщение от пользователя
     * @param range Содержит доступные пункты меню для ввода
     */
    public int ask(String phrase, int[] range) {
        boolean exist = false;
        System.out.println(phrase);
        try {
            answer = this.reader.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
        int key = Integer.valueOf(answer);
        for (int value : range) {
            if (key == value) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Ваше число отсутствует в меню.");
        }
        return key;
    }
}
