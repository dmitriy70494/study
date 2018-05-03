package ru.job4j.tracker;

import java.util.List;
import java.util.ArrayList;
/**
 * Внешний класс, расположенный в одном файле с MenuTracker
 * Редактирует заявку
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
class EditItem extends BaseAction {

    /**
     * Конструктор, задает начальные параметры действия
     *
     * @param key  номер пункта меню в массиве
     * @param name название деяствия
     */
    public EditItem(int key, String name) {
        super(key, name);
    }

    /**
     * Запускает на исполнение выбранный пункт меню
     *
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
}

/**
 * Внешний класс, расположенный в одном файле с MenuTracker
 * Реализует поиск заявок по названию.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 20.04.2018
 */
class FindName extends BaseAction {

    /**
     * Конструктор, задает начальные параметры действия
     *
     * @param key  номер пункта меню в массиве
     * @param name название деяствия
     */
    public FindName(int key, String name) {
        super(key, name);
    }

    /**
     * Запускает на исполнение выбранный пункт меню
     *
     * @param input   ввод вывод в консоль
     * @param tracker хранилище всех заявок
     */
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявок по названию --------------");
        String name = input.ask("Введите название заявок, которые нужно найти:");
        List<Item> items = tracker.findByName(name);
        if (items.size() != 0) {
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println("------------ Все заявки с названием " + name + " успешно найдены -----------");
        } else {
            System.out.println("------------ Заявки с названием " + name + " отсутствуют -----------");
        }
    }
}

/**
 * Отправляет и принимает указания на действия пользователя в меню
 *
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
    private List<UserAction> actions = new ArrayList<UserAction>();

    /**
     * Конструктор, пробрасывает начальные значения
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public List<Integer> makeRange() {
        List<Integer> range = new ArrayList<Integer>();
        for (int index = 0; index < actions.size(); index++) {
                range.add(index);
            }
        return range;
    }

    /**
     * Инициализирует события
     */
    public void fillActions() {
        this.actions.add(this.new AddItem(0, "Добавить новый элемент"));
        this.actions.add(new MenuTracker.ShowItem(1, "Показать все элементы"));
        this.actions.add(new EditItem(2, "Редактировать элемент"));
        this.actions.add(this.new DeleteItem(3, "Удалить элемент"));
        this.actions.add(new MenuTracker.FindId(4, "Найти элемент по ID"));
        this.actions.add(new FindName(5, "Найти элементы по названию"));
    }

    /**
     * Запускает на исполнение выбранный пункт меню
     *
     * @param key номер задачи из массива
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Запускает на отображение все пункты меню
     */
    public void show() {
        System.out.println("Меню.");
        for (UserAction action : this.actions) {
                System.out.println(action.info());
        }
    }

    /**
     * Внутренний класс, выполняет добавление новой заявки
     *
     * @author Dmitriy Balandin (d89086362742@yandex.ru)
     * @version $Id$
     * @since 20.04.2018
     */
    private class AddItem extends BaseAction {

        /**
         * Конструктор, задает начальные параметры действия
         *
         * @param key  номер пункта меню в массиве
         * @param name название деяствия
         */
        public AddItem(int key, String name) {
            super(key, name);
        }

        /**
         * Запускает на исполнение выбранный пункт меню
         *
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
    }

    /**
     * Внутренний статический класс, выполняет отображение всех заявок
     *
     * @author Dmitriy Balandin (d89086362742@yandex.ru)
     * @version $Id$
     * @since 20.04.2018
     */
    private static class ShowItem extends BaseAction {

        /**
         * Конструктор, задает начальные параметры действия
         *
         * @param key  номер пункта меню в массиве
         * @param name название деяствия
         */
        public ShowItem(int key, String name) {
            super(key, name);
        }

        /**
         * Запускает на исполнение выбранный пункт меню
         *
         * @param input   ввод вывод в консоль
         * @param tracker хранилище всех заявок
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Отображение всех заявок --------------");
            List<Item> items = tracker.findAll();
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }

    /**
     * Внутренний класс, удаляет заявку по ID
     *
     * @author Dmitriy Balandin (d89086362742@yandex.ru)
     * @version $Id$
     * @since 20.04.2018
     */
    private class DeleteItem extends BaseAction {

        /**
         * Конструктор, задает начальные параметры действия
         *
         * @param key  номер пункта меню в массиве
         * @param name название деяствия
         */
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        /**
         * Запускает на исполнение выбранный пункт меню
         *
         * @param input   ввод вывод в консоль
         * @param tracker хранилище всех заявок
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите ID заявки, которую нужно удалить:");
            tracker.delete(id);
            System.out.println("------------ Заявка номер " + id + " успешно удалена -----------");
        }
    }

    /**
     * Внутренний статический класс, осуществляет поиск заявки по ID
     *
     * @author Dmitriy Balandin (d89086362742@yandex.ru)
     * @version $Id$
     * @since 20.04.2018
     */
    private static class FindId extends BaseAction {

        /**
         * Конструктор, задает начальные параметры действия
         *
         * @param key  номер пункта меню в массиве
         * @param name название деяствия
         */
        public FindId(int key, String name) {
            super(key, name);
        }

        /**
         * Запускает на исполнение выбранный пункт меню
         *
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
    }
}
