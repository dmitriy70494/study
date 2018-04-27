package ru.job4j.tracker;

import java.io.IOException;

/**
 * Проверяет запросы, обрабатывает ошибки пользователя при вводе.
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 27.04.2018
 */
public class ValidateInput extends ConsoleInput {
    private int ask = -1;

    /**
     * Запрашивает ввод от Пользователя, работает пока не получит правильное число
     * @param phrase вопрос пользователю
     * @param range список допустимых пунктов меню
     */
    public int ask(String phrase, int[] range) {
        boolean invalid = true;
        do {
            try {
                ask = super.ask(phrase, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Ваше число отсутствует в меню. Введите правильное число");
            } catch (NumberFormatException nfe) {
            System.out.println("Введите запрос правильно");
            }
        } while(invalid);
        return ask;
    }
}
