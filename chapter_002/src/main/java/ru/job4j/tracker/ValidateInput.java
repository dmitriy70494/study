package ru.job4j.tracker;

import java.io.IOException;

/**
 * Проверяет запросы, обрабатывает ошибки пользователя при вводе.
 * добавляется в конструктор класса при вызове в main благодаря конструктору
 * поглащает в себя класс ConsolInput, за счет того что ссылка на этот класс
 * содержится внутри мы можем спокойно обращаться к этому классу.
 * Что будет если его не имплетировать с Imputom (все конструкторы
 * на нем построены, код сломается, когда наследовался получал интерфейс в подарок, потому в мейне
 * и ошибкка не выскакивала).
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 27.04.2018
 */
public class ValidateInput implements Input {

    private int ask = -1;

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }


    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Запрашивает ввод от Пользователя, работает пока не получит правильное число
     * @param phrase вопрос пользователю
     * @param range список допустимых пунктов меню
     */
    public int ask(String phrase, int[] range) {
        boolean invalid = true;
        do {
            try {
                ask = this.input.ask(phrase, range);
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
