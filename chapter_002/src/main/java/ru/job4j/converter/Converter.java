package ru.job4j.converter;

import java.util.Iterator;

/**
 * класс Converter. Конвертирует несколько итераторов в один.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 14.05.2018
 */
public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            /**
             * Элемент списка итератора итераторов
             */
            Iterator<Integer> iterator;

            /**
             * Инициализирует iterator
             */
            {
                if (it.hasNext()) {
                    iterator = it.next();
                }
            }

            /**
             * Проверяет следующий ход, переводит на следующий итератор
             *
             * @return true - если есть ход, false если хода больше нет
             */
            @Override
            public boolean hasNext() {
                while (!iterator.hasNext() && it.hasNext()) {
                    iterator = it.next();
                }
                return iterator.hasNext();
            }

            /**
             * Передает результат
             *
             * @return
             */
            @Override
            public Integer next() {
                this.hasNext();
                return iterator.next();
            }
        };
    }
}
