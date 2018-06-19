package ru.job4j.servlets;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import org.postgresql.Driver;
import org.apache.commons.dbcp2.BasicDataSource;

public class DBStore implements Store {

    private final static String URL_PROPERTIES = "C:/projects/study/chapter_008/src/main/resources/config.properties";

    private Properties properties;

    private Connection connection;

    private final static DBStore instance = new DBStore();

    private BasicDataSource dataSource;

    private DBStore() {

    }

    public static Store getInstance() {
        if (instance.dataSource == null || instance.properties == null) {
            try {
                instance.properties = new Properties();
                instance.properties.load(new FileInputStream(URL_PROPERTIES));
                Class.forName(new Driver().getClass().getName());
                instance.dataSource = new BasicDataSource();
                instance.dataSource.setUrl(instance.properties.getProperty("url"));
                instance.dataSource.setUsername(instance.properties.getProperty("user"));
                instance.dataSource.setPassword(instance.properties.getProperty("password"));
                instance.dataSource.setMinIdle(5);
                instance.dataSource.setMaxIdle(10);
                instance.dataSource.setMaxOpenPreparedStatements(100);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    public String add(User user) {
        String result = null;
        if (this.checkUser(this.properties.getProperty("select_user_login"), user.getLogin())) {
            result = "Пользователь с таким логином уже существует";
        }
        if (this.checkUser(this.properties.getProperty("select_user_email"), user.getEmail())) {
            result = "Пользователь с такой почтой уже существует";
        }
        if (result == null) {
            this.addUser(user);
            result = "Пользователь успешно добавлен";
        }
        return result;
    }

    public void addUser(User user) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(this.properties.getProperty("insert_user"));) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setTimestamp(4, user.getCreateDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private <T> boolean checkUser(String sql, T value) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            if (value instanceof Integer) {
                statement.setInt(1, (Integer) value);
            }else if (value instanceof String) {
                statement.setString(1, (String) value);
            }else if (value instanceof Timestamp) {
                statement.setTimestamp(1, (Timestamp) value);
            } else {
                throw new IllegalArgumentException("the value is not verified");
            }
            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("the user is not verified");
    }

    @Override
    public boolean update(String id, User user) {
        boolean access = true;
        if (this.checkUser(this.properties.getProperty("select_user_id"), Integer.valueOf(id))) {
            user.setId(Integer.valueOf(id));
            this.delete(id);
            this.addUser(user);
            access = true;
        }
        return access;
    }

    @Override
    public boolean delete(String id) {
        boolean access = false;
        Integer idi = Integer.valueOf(id);
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(this.properties.getProperty("delete_user"))) {
            if (this.checkUser(this.properties.getProperty("select_user_id"), idi)) {
                statement.setInt(1, idi);
                statement.executeUpdate();
                access = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return access;
    }

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>(100);
        try (Connection connection = this.dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(this.properties.getProperty("select_all_users"));) {
            while (result.next()) {
                users.add(new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getTimestamp(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(String id) {
        User user = null;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(this.properties.getProperty("select_user_id"))) {
            statement.setInt(1, Integer.valueOf(id));
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    user = new User(result.getInt("id"), result.getString("name"), result.getString("login"), result.getString("email"), result.getTimestamp("create_date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
