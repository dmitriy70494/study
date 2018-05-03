package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса StartUI.
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 26.04.2018
 */
public class StartUITest {

    /**
     * поток для управления выводом в консоль, наверное
     */
    private final PrintStream stdout = System.out;

    /**
     * Буфер для результата
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Генерируем поток ввода
     */
    private Input input;

    private Tracker trackers = new Tracker();

    private Item itemOne = trackers.add(new Item());
    private Item itemTwo = trackers.add(new Item());
    private Item itemThree = trackers.add(new Item());
    private Item itemFour = trackers.add(new Item());
    /**
     * Метод для операций в начале метода, аннотация @Before, нужно добавлять в импорт
     * Заменяем стандартный вывод на вывод в пямять для тестирования.
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
        itemOne.setName("one name");
        itemTwo.setName("two name");
        itemThree.setName("three name");
        itemFour.setName("one name");
    }

    /**
     * Метод для операций в конце метода, аннотация @After
     * возвращаем обратно стандартный вывод в консоль.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    /**
     * Проверка на отображение всех заявок
     */
    @Test
    public void whenUserAddItemThenTrackerHasAllItems() {
        Input input = new StrubInput(new String[]{"1", "да"});
        new StartUI(input, trackers).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Меню.\r\n0. Добавить новый элемент\r\n")
                                .append("1. Показать все элементы\r\n2. Редактировать элемент\r\n3. Удалить элемент\r\n")
                                .append("4. Найти элемент по ID\r\n5. Найти элементы по названию\r\n")
                                .append("------------ Отображение всех заявок --------------\r\n")
                                .append("Номер ID заявки: " + itemOne.getId() + "\n")
                                .append("Название заявки: one name\n")
                                .append("Описание заявки: null\n\n\r\n")
                                .append("Номер ID заявки: " + itemTwo.getId() + "\n")
                                .append("Название заявки: two name\n")
                                .append("Описание заявки: null\n\n\r\n")
                                .append("Номер ID заявки: " + itemThree.getId() + "\n")
                                .append("Название заявки: three name\n")
                                .append("Описание заявки: null\n\n\r\n")
                                .append("Номер ID заявки: " + itemFour.getId() + "\n")
                                .append("Название заявки: one name\n")
                                .append("Описание заявки: null\n\n")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    /**
     * Проверка на поиск заявки по ID
     */
    @Test
    public void whenUserAddItemThenTrackerHasFindItemsByID() {
        Input input = new StrubInput(new String[]{"4", itemThree.getId(), "да"});
        new StartUI(input, trackers).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Меню.\r\n0. Добавить новый элемент\r\n")
                                .append("1. Показать все элементы\r\n2. Редактировать элемент\r\n3. Удалить элемент\r\n")
                                .append("4. Найти элемент по ID\r\n5. Найти элементы по названию\r\n")
                                .append("------------ Поиск заявки по ID --------------\r\n")
                                .append("Номер ID заявки: " + itemThree.getId() + "\n")
                                .append("Название заявки: three name\n")
                                .append("Описание заявки: null\n\n\r\n")
                                .append("------------ Заявка номер " + itemThree.getId() + " успешно найдена -----------")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    /**
     * Проверка на поиск всех заявок с одинаковым автором
     */
    @Test
    public void whenUserAddItemThenTrackerHasFindItemsByName() {
        Input input = new StrubInput(new String[]{"5", itemFour.getName(), "да"});
        new StartUI(input, trackers).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Меню.\r\n0. Добавить новый элемент\r\n")
                                .append("1. Показать все элементы\r\n2. Редактировать элемент\r\n3. Удалить элемент\r\n")
                                .append("4. Найти элемент по ID\r\n5. Найти элементы по названию\r\n")
                                .append("------------ Поиск заявок по названию --------------\r\n")
                                .append("Номер ID заявки: " + itemOne.getId() + "\n")
                                .append("Название заявки: one name\n")
                                .append("Описание заявки: null\n\n\r\n")
                                .append("Номер ID заявки: " + itemFour.getId() + "\n")
                                .append("Название заявки: one name\n")
                                .append("Описание заявки: null\n\n\r\n")
                                .append("------------ Все заявки с названием one name успешно найдены -----------")

                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    /**
     * Проверка на поиск всех заявок с одинаковым автором, но пустым значением
     */
    @Test
    public void whenUserAddItemThenTrackerHasFindItemsByNameNull() {
        Input input = new StrubInput(new String[]{"5", itemFour.getId(), "да"});
        new StartUI(input, trackers).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Меню.\r\n0. Добавить новый элемент\r\n")
                                .append("1. Показать все элементы\r\n2. Редактировать элемент\r\n3. Удалить элемент\r\n")
                                .append("4. Найти элемент по ID\r\n5. Найти элементы по названию\r\n")
                                .append("------------ Поиск заявок по названию --------------\r\n")
                                .append("------------ Заявки с названием " + itemFour.getId() + " отсутствуют -----------")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    /**
     * Проверка на добавление элемента
     * создаём Tracker
     * создаём StubInput с последовательностью действий
     * создаём StartUI и вызываем метод init()
     * проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StrubInput(new String[]{"0", "test name", "desc", "да"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    /**
     * Проверка редактирования элемента по ID
     * создаём Tracker
     * Напрямую добавляем заявку
     * создаём StubInput с редактированием заявки
     * создаём StartUI и вызываем метод init()
     * проверяем, что добавленная заявка содержит имя, введённое при эмуляции редактирования.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StrubInput(new String[]{"2", item.getId(), "test name", "desc", "да"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    /**
     * Проверка удаления элемента и смещения индексов
     * создаём Tracker
     * Напрямую добавляем две заявки
     * второй заявке задаем имя
     * создаём StubInput с удалением первой заявки по ID
     * создаём StartUI и вызываем метод init()
     * проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции
     * второму элементу.
     */
    @Test
    public void whenUpdateThenTrackerHasShowAllValue() {
        Tracker tracker = new Tracker();
        Item itemOne = tracker.add(new Item());
        Item itemTwo = tracker.add(new Item());
        Item itemThree = tracker.add(new Item());
        itemTwo.setName("test name");
        Input input = new StrubInput(new String[]{"3", itemOne.getId(), "да"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    /**
     * проверка индекса добавления элемента, после удаления предыдущих
     * создаём Tracker
     * Напрямую добавляем две заявки
     * второй заявке задаем имя
     * создаём StubInput с удалением первой заявки по ID + добавление третьей заявки
     * создаём StartUI и вызываем метод init()
     * проверяем, что первый элемент массива в трекере содержит имя, введённое при эмуляции
     * третьему элементу.
     */
    @Test
    public void whenUpdateThenTrackerHasShowAllValuePosition() {
        Tracker tracker = new Tracker();
        Item itemOne = tracker.add(new Item());
        Item itemTwo = tracker.add(new Item());
        itemTwo.setName("two name");
        Input input = new StrubInput(new String[]{"3", itemOne.getId(), "нет", "0", "three name",  "desc", "да"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(1).getName(), is("three name"));
    }
}
