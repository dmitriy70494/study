package ru.job4j.tracker;

import java.util.ArrayList;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса Tracker.
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 24.04.2018
 */

public class TrackerTest {

    @Test
    public void whenItemsDelete() {
        Tracker resultTracker = new Tracker().init("settings.sql");
        Item oneItem = new Item();
        Item twoItem = new Item();
        resultTracker.add(oneItem);
        resultTracker.add(twoItem);
        String oneId = oneItem.getId();
        String twoId = twoItem.getId();
        resultTracker.delete(twoId);
        String exept = null;
        assertThat(resultTracker.findById(twoId), is(exept));
    }

    @Test
    public void whenItemsReplace() {
        Tracker resultTracker = new Tracker().init("settings.sql");
        Item oneItem = new Item();
        resultTracker.add(oneItem);
        String oneId = oneItem.getId();
        Item testItem = new Item("name", "desc", System.currentTimeMillis());
        resultTracker.replace(oneId, testItem);
        ArrayList<Item> except = new ArrayList<Item>();
        except.add(testItem);
        assertThat(resultTracker.findById(oneId).getName(), is("name"));
    }

    @Test
    public void whenItemsFindAll() {
        Tracker resultTracker = new Tracker().init("settings.sql");
        assertThat(resultTracker.findAll().size() > 7, is(true));
    }

    @Test
    public void whenItemsFindByName() {
        Tracker resultTracker = new Tracker().init("settings.sql");
        assertThat(resultTracker.findByName("Мастер").size() > 2, is(true));
    }


}
