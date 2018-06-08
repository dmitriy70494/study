package ru.job4j.magnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


/**
 * 1. Генерация данных в SQLLite. Описывается классом StoreSQL
 */
public class StoreSQL {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreSQL.class);

    private Config config;

    private String[] commands;

    /**
     * StoreSQL(Config) - Config - объект содержащий настройки для подключения к базе.
     *
     * @param config
     */
    public StoreSQL(Config config) {
        this.config = config;
        this.commands = config.getCommands();
    }

    /**
     * метод generate(int n) - генерирует в базе данных n записей.
     * <p>
     * описывается схемой
     * <p>
     * create table entry {
     * field integer;
     * } - нужно создать таблицу такого вида
     * 2.1) Если таблица entry в БД отсутствует, то создает ее.
     * 2.2) вставляет в таблицу entry n записей со значениями 1..N. Если в таблице account
     * находились записи, то они удаляются перед вставкой.
     *
     * Для вычислений сначала использовал обычный запрос инсерт c avtocommit время выполнения 1 000 000 более 5 минут
     * убрав автокоммит получилось около 1 минуты
     * используя встроенную функцию удалось уложиться в 8 секунд
     *
     * @param size
     */
    public void generate(int size) {
        try (Connection connection = DriverManager.getConnection(config.command(commands[0]), config.command(commands[1]), config.command(commands[2]));
             Statement statement = connection.createStatement()) {
            this.generateTable(statement);
            do {
                CallableStatement upperProc = connection.prepareCall("{call add_value_insert( ? ) }");
                upperProc.setInt(1, size);
                upperProc.execute();
                upperProc.close();
            } while (!this.checkTable(statement, size));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void generateTable(Statement statement) throws SQLException {
        if (config == null) {
            throw new IllegalArgumentException("Config null");
        }
        if (!statement.executeQuery(config.command(commands[3])).next()) {
            statement.execute(config.command(commands[4]));
        }
    }

    private boolean checkTable(Statement statement, int size) throws SQLException {
        int check = 0;
        statement.executeQuery(config.command(commands[7]));
        ResultSet result = statement.getResultSet();
        if (result.next()) {
            check = result.getInt(1);
        }
        return check == size;
    }
}
