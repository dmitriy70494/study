package ru.job4j.tracker;

import org.junit.Test;
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
     * Проверка на добавление элемента
     * создаём Tracker
     * создаём StubInput с последовательностью действий
     * создаём StartUI и вызываем метод init()
     * проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StrubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
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
        Input input = new StrubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
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
        Input input = new StrubInput(new String[]{"3", itemOne.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
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
        Input input = new StrubInput(new String[]{"3", itemOne.getId(), "0", "three name", "desc",  "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[1].getName(), is("three name"));
    }
}
