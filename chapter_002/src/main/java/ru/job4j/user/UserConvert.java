package ru.job4j.user;

import java.util.List;
import java.util.HashMap;
/**
 * Класс UserConvert. Конвертирует данные из одной структуры данных в другую
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 03.05.2018
 */
public class UserConvert {

    /**
     * Преобразует обычный список List в список ключ-значение HashMap
     *
     * @param list список пользователей
     * @return список ключ-значение
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> mapList = new HashMap<Integer, User>();
        for (User user : list) {
            mapList.put(user.getId(), user);
        }
        return mapList;
    }
}
