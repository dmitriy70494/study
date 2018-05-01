package ru.job4j.search;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса PhoneDictionary. Добавляем три персоны в книгу и проверяем поиск
 * по ключу.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 01.05.2018
 */
public class PhoneDictionaryTest {

    PhoneDictionary phones = new PhoneDictionary();

    @Before
    public void loadOutput() {
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Dmitriy", "Balandin", "9086362742", "Sverdlovsk")
        );
        phones.add(
                new Person("Ivan", "Karsanev", "9086405519", "Cosmopolit")
        );
    }

    @Test
    public void whenFindByName() {
        List<Person> persons = phones.find("Petr");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindByTwoName() {
        List<Person> persons = phones.find("ev");
        assertThat(persons.get(1).getSurname(), is("Karsanev"));
    }

    @Test
    public void whenFindByPhone() {
        List<Person> persons = phones.find("908");
        assertThat(persons.get(0).getSurname(), is("Balandin"));
    }
}