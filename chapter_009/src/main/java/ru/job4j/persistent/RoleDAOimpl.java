package ru.job4j.persistent;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class RoleDAOimpl<Role> implements RoleDAO<Role> {

    private static boolean availability = true;

    private final Properties properties = new Properties();

    private DataSource data;

    private RoleDAOimpl() {
    }

    public static RoleDAO getInstance(String properties, DataSource data) throws IOException {
        RoleDAOimpl roles = null;
        if (availability) {
            availability = false;
            roles = new RoleDAOimpl();
            roles.properties.load(new FileInputStream(roles.getClass().getResource(properties).getPath()));
            roles.data = data;
        }
        return roles;
    }

    @Override
    public boolean add(Role obj) {
        return false;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Role findById(String id) {
        return null;
    }

    @Override
    public boolean update(Role obj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
