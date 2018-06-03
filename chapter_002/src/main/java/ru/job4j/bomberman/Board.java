package ru.job4j.bomberman;

/**
 * класс Board. Хранит поле с ячейками для ходов.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 01.06.2018
 */
public class Board {

    /**
     * поле
     */
    private final Cell[][] board;

    /**
     * используется для прекращения работы автоматических нитей когда игра окончена
     */
    private boolean worked = true;


    /**
     * Конструктор инициализирует размер поля
     * @param size
     */
    public Board(int size) {
        board = new Cell[size][size];
    }

    /**
     * Добавляет ячейки в поле и запускает 2 нити героев, которые делают ход каждую секунду, если ходов нет, то выбрасывают
     * исключение NoMovesException
     * @return
     */
    public Thread[] init() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                board[y][x] = new Cell(y, x);
            }
        }
        Thread[] threads = new Thread[2];
        for (int index = 0; index < threads.length; index++) {
            threads[index] = new Thread() {

                @Override
                public void run() {
                    int size = board.length;
                    int y = (int) (Math.random() * (size - 1));
                    int x = (int) (Math.random() * (size - 1));
                    long start = System.currentTimeMillis();
                    boolean access = board[y][x].lock();
                    int[] step = {0, 0, 1, -1};
                    while (worked) {
                        int count = 0;
                        while (!access && count < 4) {
                            if (new Cell(y + step[count], x + step[3 - count]).exist(size)) {
                                access = move(board[y][x], board[y + step[count]][x + step[3 - count]]);
                            }
                            if (access) {
                                y += step[count];
                                x += step[3 - count];
                                count = -1;
                            }
                            count++;
                        }
                        if (count != 0) {
                            throw new NoMovesException();
                        }
                        try {
                            access = false;
                            long time = 1000 - ( - start);
                            sleep(time = time < 0 ? 0 : time);
                            start = System.currentTimeMillis();
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                }
            };
            threads[index].start();
        }
        return threads;
    }

    /**
     * Если эти клетки соседние по вертикали или горизонтали и source заблокирована этим потоком, и клетку dist можно этому потоку заблокировать
     * то поток разблокирует свою клетку и уже заблокировал на перемещаемую.
     *
     * @param source
     * @param dist
     * @return
     */
    public boolean move(Cell source, Cell dist) {
        if (source.isNeighbor(dist) && source.lock() && dist.lock()) {
            source.unlock();
        }
        return dist.lock();
    }

    /**
     * блокирует любую клетку поля согласно координатам
     * @param y
     * @param x
     * @return
     */
    public boolean block(int y, int x) {
        return board[y][x].lock();
    }
}
