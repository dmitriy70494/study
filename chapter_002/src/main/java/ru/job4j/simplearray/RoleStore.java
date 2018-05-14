package ru.job4j.simplearray;

/**
 * класс RoleStore. Хранит Role.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 14.05.2018
 */
public class RoleStore<Role extends Base> extends AbstractStore<Role> {
    public RoleStore(int size) {
        super(size);
    }
}
