package ru.job4j.simplearray;

/**
 * класс UserStore. Хранит User.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 14.05.2018
 */
public class UserStore<User extends Base> extends AbstractStore<User> {
    public UserStore(int size) {
        super(size);
    }
}
