package ru.job4j.persistent;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class MusicTypeDAOimpl<MusicType> implements MusicTypeDAO<MusicType> {

    private static boolean availability = true;

    private final Properties properties = new Properties();

    private DataSource data;

    private MusicTypeDAOimpl() {
    }

    public static MusicTypeDAO getInstance(String properties, DataSource data) throws IOException {
        MusicTypeDAOimpl types = null;
        if (availability) {
            availability = false;
            types = new MusicTypeDAOimpl();
            types.properties.load(new FileInputStream(types.getClass().getResource(properties).getPath()));
            types.data = data;
        }
        return types;
    }

    @Override
    public boolean add(MusicType obj) {
        return false;
    }

    @Override
    public List<MusicType> findAll() {
        return null;
    }

    @Override
    public MusicType findById(String id) {
        return null;
    }

    @Override
    public boolean update(MusicType obj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
