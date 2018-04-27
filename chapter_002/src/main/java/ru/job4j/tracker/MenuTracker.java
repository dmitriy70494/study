package ru.job4j.tracker;

/**
 * Внешний класс, расположенный в одном файле с MenuTracker
 * Редактирует заявку
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
class EditItem implements UserAction {

    /**
     * возвращает номер пункта меню
     * @return int номер пункта меню в массиве
     */
    public int key() {
        return 2;
    }

    /**
     * Запускает на исполнение выбранный пункт меню
     * @param input   ввод вывод в консоль
     * @param tracker хранилище всех заявок
     */
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Редактирование заявки --------------");
        String id = input.ask("Введите ID заявки, которую нужно изменить:");
        String name = input.ask("Введите новое имя заявки:");
        String desc = input.ask("Введите новое описание заявки:");
        long creator = System.currentTimeMillis();
        Item item = new Item(name, desc, creator);
        tracker.replace(id, item);
        System.out.println("------------ Заявка номер " + id + " успешно изменена -----------");
    }

    /**
     * отображает пунк меню для отображения пользователю
     * @return String отображает пунк меню для отображения пользователю
     */
    public String info() {
        return String.format("%s. %s", this.key(), "Редактировать элемент");
    }
}

/**
 * Внешний класс, расположенный в одном файле с MenuTracker
 * Реализует поиск заявок по названию.
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
class FindName implements UserAction {

    /**
     * возвращает номер пункта меню
     * @return int номер пункта меню в массиве
     */
    public int key() {
        return 5;
    }

    /**
     * Запускает на исполнение выбранный пункт меню
     * @param input   ввод вывод в консоль
     * @param tracker хранилище всех заявок
     */
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявок по названию --------------");
        String name = input.ask("Введите название заявок, которые нужно найти:");
        Item[] items = tracker.findByName(name);
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
     * отображает пунк меню для отображения пользователю
     * @return String отображает пунк меню для отображения пользователю
     */
    public String info() {
        return String.format("%s. %s", this.key(), "Найти элементы по названию");
    }
}

/**
 * Отправляет и принимает указания на действия пользователя в меню
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
public class MenuTracker {

    /**
     * Генерирует поток ввода-вывода в консоль
     */
    private Input input;

    /**
     * Хранит и обрабатывает заявки
     */
    private Tracker tracker;

    /**
     * Хранит действия пользователя
     */
    private UserAction[] actions = new UserAction[6];

    /**
     * Конструктор, пробрасывает начальные значения
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public int[] makeRange() {
        int size = actions.length;
        int[] range = new int[size];
        for (int index = 0; index < size; index++) {
            if(this.actions[index] != null) {
                range[index] = index;
            }
        }
        return range;
    }
    /**
     * Инициализирует события
     */
    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowItem();
        this.actions[2] = new EditItem();
        this.actions[3] = this.new DeleteItem();
        this.actions[4] = new MenuTracker.FindId();
        this.actions[5] = new FindName();
    }

    /**
     * Запускает на исполнение выбранный пункт меню
     * @param key номер задачи из массива
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Запускает на отображение все пункты меню
     */
    public void show() {
        System.out.println("Меню.");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Внутренний класс, выполняет добавление новой заявки
     * @author Dmitriy Balandin (d89086362742@yandex.ru)
     * @version $Id$
     * @since 20.04.2018
     */
    private class AddItem implements UserAction {

        /**
         * возвращает номер пункта меню
         * @return int номер пункта меню в массиве
         */
        public int key() {
            return 0;
        }

        /**
         * Запускает на исполнение выбранный пункт меню
         * @param input   ввод вывод в консоль
         * @param tracker хранилище всех заявок
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки:");
            String desc = input.ask("Введите описание заявки:");
            long creator = System.currentTimeMillis();
            Item item = new Item(name, desc, creator);
            tracker.add(item);
            System.out.println("------------ Новая заявка с getId: " + item.getId() + " -----------");
        }

        /**
         * отображает пунк меню для отображения пользователю
         * @return String отображает пунк меню для отображения пользователю
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Добавить новый элемент");
        }
    }

    /**
     * Внутренний статический класс, выполняет отображение всех заявок
     * @author Dmitriy Balandin (d89086362742@yandex.ru)
     * @version $Id$
     * @since 20.04.2018
     */
    private static class ShowItem implements UserAction {

        /**
         * возвращает номер пункта меню
         * @return int номер пункта меню в массиве
         */
        public int key() {
            return 1;
        }

        /**
         * Запускает на исполнение выбранный пункт меню
         * @param input   ввод вывод в консоль
         * @param tracker хранилище всех заявок
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Отображение всех заявок --------------");
            Item[] items = tracker.findAll();
            for (Item item : items) {
                System.out.println(item);
            }
        }

        /**
         * отображает пунк меню для отображения пользователю
         * @return String отображает пунк меню для отображения пользователю
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Показать все элементы");
        }
    }

    /**
     * Внутренний класс, удаляет заявку по ID
     * @author Dmitriy Balandin (d89086362742@yandex.ru)
     * @version $Id$
     * @since 20.04.2018
     */
    private class DeleteItem implements UserAction {

        /**
         * возвращает номер пункта меню
         * @return int номер пункта меню в массиве
         */
        public int key() {
            return 3;
        }

        /**
         * Запускает на исполнение выбранный пункт меню
         * @param input   ввод вывод в консоль
         * @param tracker хранилище всех заявок
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите ID заявки, которую нужно удалить:");
            tracker.delete(id);
            System.out.println("------------ Заявка номер " + id + " успешно удалена -----------");
        }

        /**
         * отображает пунк меню для отображения пользователю
         * @return String отображает пунк меню для отображения пользователю
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Удалить элемент");
        }
    }

    /**
     * Внутренний статический класс, осуществляет поиск заявки по ID
     * @author Dmitriy Balandin (d89086362742@yandex.ru)
     * @version $Id$
     * @since 20.04.2018
     */
    private static class FindId implements UserAction {

        /**
         * возвращает номер пункта меню
         * @return int номер пункта меню в массиве
         */
        public int key() {
            return 4;
        }

        /**
         * Запускает на исполнение выбранный пункт меню
         * @param input   ввод вывод в консоль
         * @param tracker хранилище всех заявок
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по ID --------------");
            String id = input.ask("Введите ID заявки, которую нужно найти:");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println(item);
                System.out.println("------------ Заявка номер " + id + " успешно найдена -----------");
            } else {
                System.out.println("------------ Заявка номер " + id + " отсутствует -----------");
            }
        }

        /**
         * отображает пунк меню для отображения пользователю
         * @return String отображает пунк меню для отображения пользователю
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Найти элемент по ID");
        }
    }
}
