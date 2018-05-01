package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * класс PhoneDictionary. Добавляет персоны в книгу, также может осуществлять поиск
 * по ключу.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 01.05.2018
 */
public class PhoneDictionary {

    /**
     * книга
     */
    private List<Person> persons = new ArrayList<Person>();

    /**
     * добавляет заполненных персон в книгу
     *
     * @param person персона для заполнения в книгу
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<Person>();
        for (Person person : persons) {
            boolean ifFind = new StringBuffer()
                    .append(person.getName() + " ")
                    .append(person.getSurname() + " ")
                    .append(person.getAddress() + " ")
                    .append(person.getPhone())
                    .toString().contains(key);
            if (ifFind) {
                result.add(person);
            }
        }
        return result;
    }
}