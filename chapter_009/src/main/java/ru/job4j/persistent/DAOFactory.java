package ru.job4j.persistent;

import org.apache.commons.dbcp2.BasicDataSource;
import org.postgresql.Driver;
import ru.job4j.logic.Address;
import ru.job4j.logic.MusicType;
import ru.job4j.logic.Role;
import ru.job4j.logic.User;

import java.io.*;
import java.util.Properties;

public class DAOFactory {

    private final Properties properties = new Properties();

    private final static String FILE_PROPERTIES = "/database.properties";

    private final static String FILE_PROPERTIES_USER = "/usertable.properties";

    private final static String FILE_PROPERTIES_ROLE = "/roletable.properties";

    private final static String FILE_PROPERTIES_ADDRESS = "/addresstable.properties";

    private final static String FILE_PROPERTIES_TYPE = "/musicaltypetable.properties";

    private final static DAOFactory instance = new DAOFactory();

    private BasicDataSource dataSource;

    private UserDAO<User> users;

    private RoleDAO<Role> roles;

    private AddressDAO<Address> addresses;

    private MusicTypeDAO<MusicType> types;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        if (instance.dataSource == null) {
            try {
                instance.properties.load(new FileInputStream(instance.getClass().getResource(FILE_PROPERTIES).getPath()));
                Class.forName(new Driver().getClass().getName());
                instance.dataSource = new BasicDataSource();
                instance.dataSource.setUrl(instance.properties.getProperty("url"));
                instance.dataSource.setUsername(instance.properties.getProperty("user"));
                instance.dataSource.setPassword(instance.properties.getProperty("password"));
                instance.dataSource.setMinIdle(5);
                instance.dataSource.setMaxIdle(10);
                instance.dataSource.setMaxOpenPreparedStatements(100);
                instance.initDAO();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * синхронизация нужна чтобы при одновременной инициализвации 2 потоков они не испортили данные
     * @throws IOException
     */
    private synchronized void initDAO() throws IOException {
        if (this.users == null) {
            this.users = UserDAOimpl.getInstance(FILE_PROPERTIES_USER, dataSource);
        }
        if (this.roles == null) {
            this.roles = RoleDAOimpl.getInstance(FILE_PROPERTIES_ROLE, dataSource);
        }
        if (this.addresses == null) {
            this.addresses = AddressDAOimpl.getInstance(FILE_PROPERTIES_ADDRESS, dataSource);
        }
        if (this.types == null) {
            this.types = MusicTypeDAOimpl.getInstance(FILE_PROPERTIES_TYPE, dataSource);
        }
        if (this.users == null || this.roles == null || this.addresses == null || this.types == null) {
            throw new IllegalArgumentException("one object = null in initDAO");
        }
    }

    public UserDAO getUserDAO() {
        return this.users;
    }

    public AddressDAO getAddressDAO() {
        return this.addresses;
    }

    public RoleDAO getRoleDAO() {
        return this.roles;
    }

    public MusicTypeDAO getMusicTypeDAO() {
        return this.types;
    }
}
