package ru.job4j.persistent;

import ru.job4j.logic.User;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class UserDAOimpl<T extends User> implements UserDAO<T> {

    private static boolean availability = true;

    private final Properties properties = new Properties();

    private DataSource source;

    private UserDAOimpl() {
    }

    public static UserDAO getInstance(String properties, DataSource source) throws IOException, SQLException {
        UserDAOimpl users = null;
        if (availability) {
            availability = false;
            users = new UserDAOimpl();
            users.properties.load(new FileInputStream(users.getClass().getResource(properties).getPath()));
            users.source = source;
            Connection conn = source.getConnection();
            Statement statement = conn.createStatement();
            if (!statement.executeQuery(users.properties.getProperty("check_table")).next()) {
                statement.execute(users.properties.getProperty("create_table"));
            }
        }
        return users;
    }

    @Override
    public boolean add(User user) {
        try (Connection conn = this.source.getConnection();
             PreparedStatement statement = conn.prepareStatement(this.properties.getProperty("insert_user"))) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setInt(4, Integer.valueOf(user.getRole().getId()));
            statement.setInt(5, Integer.valueOf(user.getAddress().getId()));
            return statement.executeUpdate() == 5;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("user not add");
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public T findById(String id) {
        return null;
    }

    @Override
    public boolean update(User obj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List query(AccountSpecification specification) {
        return null;
    }

}
