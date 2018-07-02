package ru.job4j.persistent;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class AddressDAOimpl<Address> implements AddressDAO<Address> {

    private static boolean availability = true;

    private final Properties properties = new Properties();

    private DataSource data;

    private AddressDAOimpl() {
    }

    public static AddressDAO getInstance(String properties, DataSource data) throws IOException {
        AddressDAOimpl addresses = null;
        if (availability) {
            availability = false;
            addresses = new AddressDAOimpl();
            addresses.properties.load(new FileInputStream(addresses.getClass().getResource(properties).getPath()));
            addresses.data = data;
        }
        return addresses;
    }

    @Override
    public boolean add(Object obj) {
        return false;
    }

    @Override
    public List<Address> findAll() {
        return null;
    }

    @Override
    public Address findById(String id) {
        return null;
    }

    @Override
    public boolean update(Address obj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
