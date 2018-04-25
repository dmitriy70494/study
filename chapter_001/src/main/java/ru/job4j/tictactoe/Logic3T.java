package ru.job4j.tictactoe;

/**
 * Проверка пустых клеток + проверки на победителя.
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 25.04.2018
 */

public class Logic3T {
    private final Figure3T[][] table;

    /**
     * конструктор передает в класс готовую таблицу
     * @param table таблица с данными по клеткам
     */
    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }
    /**
     * Проверяет наличие победителя среди крестиков
     * сначала проверка по горизонтали и вертикали
     * затем проверка по диагоналям на победителя
     * @return boolean true - есть победитель среди Х.
     */
    public boolean isWinnerX() {
        for (int i = 0; i < 3; i++) {
            if (table[i][0].hasMarkX() && table[i][1].hasMarkX() && table[i][2].hasMarkX() || table[0][i].hasMarkX() && table[1][i].hasMarkX() && table[2][i].hasMarkX()) {
                return true;
            }
        }
        if (table[0][0].hasMarkX() && table[1][1].hasMarkX() && table[2][2].hasMarkX() || table[0][2].hasMarkX() && table[1][1].hasMarkX() && table[2][0].hasMarkX()) {
            return true;
        }
        return false;
    }

    /**
     * Проверяет наличие победителя среди ноликов
     * сначала проверка по горизонтали и вертикали
     * затем проверка по диагоналям на победителя
     * @return boolean true - есть победитель среди Х.
     */
    public boolean isWinnerO() {
        for (int i = 0; i < 3; i++) {
            if (table[i][0].hasMarkO() && table[i][1].hasMarkO() && table[i][2].hasMarkO() || table[0][i].hasMarkO() && table[1][i].hasMarkO() && table[2][i].hasMarkO()) {
                return true;
            }
        }
        if (table[0][0].hasMarkO() && table[1][1].hasMarkO() && table[2][2].hasMarkO() || table[0][2].hasMarkO() && table[1][1].hasMarkO() && table[2][0].hasMarkO()) {
            return true;
        }
        return false;
    }

    /**
     * поиск незанятых ячеек, если не находит, выводится диалоговое окно с предложением начать
     * игру заново
     * @return boolean true - если есть свободные ячейки для хода.
     */
    public boolean hasGap() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!table[i][j].hasMarkO() && !table[i][j].hasMarkX()) {
                    return true;
                }
            }

        }
        return false;
    }
}