package ru.job4j.tracker;

/**
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа меню показывает все заявки.
     */
    private static final String SHOW = "1";

    /**
     * Константа меню для редактирования заявки .
     */
    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";

    /**
     * Константа меню для поиска заявки по ID.
     */
    private static final String FIND_ID = "4";

    /**
     * Константа меню для поиска заявки по названию.
     */
    private static final String FIND_NAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";

    /**
     * Получение данных от пользователя.
     */
    private final ConsoleInput input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(ConsoleInput input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     * Отображается меню, пользователь вводит цифру и запускает алгоритм
     * 0 - добавление новой заявки.
     * 1 - отображение всех заявок.
     * 2 - редактирование заявки.
     * 3 - удаление заявки.
     * 4 - поиск заявки по ID.
     * 5 - поиск заявок по названию.
     * 6 - Выход из программы
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showItem();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_ID.equals(answer)) {
                this.findId();
            } else if (FIND_NAME.equals(answer)) {
                this.findName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            } else {
                System.out.println("Команда введена неправильно");
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки:");
        String desc = this.input.ask("Введите описание заявки:");
        long creator = System.currentTimeMillis();
        Item item = new Item(name, desc, creator);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId: " + item.getId() + " -----------");
    }

    /**
     * Метод реализует отображение всех существующих введенных заявок.
     */
    private void showItem() {
        System.out.println("------------ Отображение всех заявок --------------");
        Item[] items = this.tracker.findAll();
        for (Item item : items) {
            System.out.println(item);
        }
    }

    /**
     * Метод реализует редактирование заявки.
     */
    private void editItem() {
        System.out.println("------------ Редактирование заявки --------------");
        String id = this.input.ask("Введите ID заявки, которую нужно изменить:");
        String name = this.input.ask("Введите новое имя заявки:");
        String desc = this.input.ask("Введите новое описание заявки:");
        long creator = System.currentTimeMillis();
        Item item = new Item(name, desc, creator);
        this.tracker.replace(id, item);
        System.out.println("------------ Заявка номер " + id + " успешно изменена -----------");
    }

    /**
     * Метод реализует удаление заявки.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите ID заявки, которую нужно удалить:");
        this.tracker.delete(id);
        System.out.println("------------ Заявка номер " + id + " успешно удалена -----------");
    }

    /**
     * Метод реализует поиск заявки по ID.
     */
    private void findId() {
        System.out.println("------------ Поиск заявки по ID --------------");
        String id = this.input.ask("Введите ID заявки, которую нужно найти:");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.println(item);
            System.out.println("------------ Заявка номер " + id + " успешно найдена -----------");
        } else {
            System.out.println("------------ Заявка номер " + id + " отсутствует -----------");
        }
    }

    /**
     * Метод реализует поиск заявок по названию.
     */
    private void findName() {
        System.out.println("------------ Поиск заявок по названию --------------");
        String name = this.input.ask("Введите название заявок, которые нужно найти:");
        Item[] items = this.tracker.findByName(name);
        if (items.length != 0) {
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println("------------ Все заявки с названием " + name + " успешно найдены -----------");
        } else {
            System.out.println("------------ Заявки с названием " + name + " отсутствуют -----------");
        }
    }

    /**
     * Метод реализует отображение меню.
     */
    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Добавить новый элемент");
        System.out.println("1. Показать все элементы");
        System.out.println("2. Редактировать элемент");
        System.out.println("3. Удалить элемент");
        System.out.println("4. Найти элемент по ID");
        System.out.println("5. Найти элементы по названию");
        System.out.println("6. Выход из программы");
    }

    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
