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
        Tracker resultTracker = new Tracker();
        Item oneItem = new Item();
        Item twoItem = new Item();
        Item threeItem = new Item();
        oneItem = resultTracker.add(oneItem);
        twoItem = resultTracker.add(twoItem);
        threeItem = resultTracker.add(threeItem);
        String oneId = oneItem.getId();
        String twoId = twoItem.getId();
        String threeId = threeItem.getId();
        ArrayList<Item> except = new ArrayList<Item>();
        except.add(oneItem);
        except.add(threeItem);
        resultTracker.delete(twoId);
        assertThat(resultTracker.getItems(), is(except));
    }

    @Test
    public void whenItemsDeleteNull() {
        Tracker resultTracker = new Tracker();
        Item oneItem = new Item();
        Item twoItem = new Item();
        Item threeItem = new Item();
        oneItem = resultTracker.add(oneItem);
        twoItem = resultTracker.add(twoItem);
        threeItem = resultTracker.add(threeItem);
        String oneId = oneItem.getId();
        String twoId = twoItem.getId();
        String threeId = threeItem.getId();
        ArrayList<Item> except = new ArrayList<Item>();
        except.add(oneItem);
        except.add(twoItem);
        except.add(threeItem);
        resultTracker.delete("100");
        assertThat(resultTracker.getItems(), is(except));
    }

    @Test
    public void whenItemsAdd() {
        Tracker resultTracker = new Tracker();
        Item oneItem = new Item();
        oneItem = resultTracker.add(oneItem);
        ArrayList<Item> except = new ArrayList<Item>();
        except.add(oneItem);
        assertThat(resultTracker.getItems(), is(except));
    }

    @Test
    public void whenItemsReplace() {
        Tracker resultTracker = new Tracker();
        Item oneItem = new Item();
        oneItem = resultTracker.add(oneItem);
        String oneId = oneItem.getId();
        Item testItem = new Item("name", "desc", System.currentTimeMillis());
        resultTracker.replace(oneId, testItem);
        testItem.setId(oneId);
        ArrayList<Item> except = new ArrayList<Item>();
        except.add(testItem);
        assertThat(resultTracker.getItems(), is(except));
    }

    @Test
    public void whenItemsReplaceNullId() {
        Tracker resultTracker = new Tracker();
        Item oneItem = new Item();
        oneItem = resultTracker.add(oneItem);
        String oneId = oneItem.getId();
        Item testItem = new Item("name", "desc", System.currentTimeMillis());
        resultTracker.replace("100", testItem);
        testItem.setId(oneId);
        ArrayList<Item> except = new ArrayList<Item>();
        except.add(oneItem);
        assertThat(resultTracker.getItems(), is(except));
    }

    @Test
    public void whenItemsFindAll() {
        Tracker resultTracker = new Tracker();
        Item oneItem = new Item();
        Item twoItem = new Item();
        Item threeItem = new Item();
        oneItem = resultTracker.add(oneItem);
        twoItem = resultTracker.add(twoItem);
        threeItem = resultTracker.add(threeItem);
        ArrayList<Item> except = new ArrayList<Item>();
        except.add(oneItem);
        except.add(twoItem);
        except.add(threeItem);
        assertThat(resultTracker.findAll(), is(except));
    }

    @Test
    public void whenItemsFindAllNull() {
        Tracker resultTracker = new Tracker();
        ArrayList<Item> except = new ArrayList<Item>();
        assertThat(resultTracker.findAll(), is(except));
    }

    @Test
    public void whenItemsFindByName() {
        Tracker resultTracker = new Tracker();
        Item oneItem = new Item();
        Item twoItem = new Item();
        Item threeItem = new Item();
        oneItem.setName("Мастер");
        twoItem.setName("Маргарита");
        threeItem.setName("Мастер");
        oneItem = resultTracker.add(oneItem);
        twoItem = resultTracker.add(twoItem);
        threeItem = resultTracker.add(threeItem);
        ArrayList<Item> except = new ArrayList<Item>();
        except.add(oneItem);
        except.add(threeItem);
        assertThat(resultTracker.findByName("Мастер"), is(except));
    }

    @Test
    public void whenItemsFindByNameNull() {
        Tracker resultTracker = new Tracker();
        Item oneItem = new Item();
        Item twoItem = new Item();
        Item threeItem = new Item();
        oneItem.setName("Мастер");
        twoItem.setName("Маргарита");
        threeItem.setName("Мастер");
        oneItem = resultTracker.add(oneItem);
        twoItem = resultTracker.add(twoItem);
        threeItem = resultTracker.add(threeItem);
        ArrayList<Item> except = new ArrayList<Item>();
        assertThat(resultTracker.findByName("Мастерv"), is(except));
    }

    @Test
    public void whenItemsFindById() {
        Tracker resultTracker = new Tracker();
        Item oneItem = new Item();
        Item twoItem = new Item();
        Item threeItem = new Item();
        oneItem = resultTracker.add(oneItem);
        twoItem = resultTracker.add(twoItem);
        threeItem = resultTracker.add(threeItem);
        String threeId = threeItem.getId();
        assertThat(resultTracker.findById(threeId), is(threeItem));
    }

    @Test
    public void whenItemsFindByIdNull() {
        Tracker resultTracker = new Tracker();
        Item oneItem = new Item();
        Item twoItem = new Item();
        Item threeItem = new Item();
        oneItem = resultTracker.add(oneItem);
        twoItem = resultTracker.add(twoItem);
        threeItem = resultTracker.add(threeItem);
        threeItem = null;
        assertThat(resultTracker.findById("100"), is(threeItem));
    }
}
