package ru.job4j.magnit;

import org.junit.Test;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.*;

/**
 * время выполнения программы для int size = 1_000_000 составляет 16 секунд
 */
public class StoreSQLTest {

    @Test
    public void whenCreateTableMustIsEmpty() throws SQLException {
        /**File source = new File("target.xml");
        File dest = new File("convert.xml");
        int size = 10;
        Config config = Config.initialConfig("magnit.sql");
        String[] commands = config.getCommands();
        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.generate(size);
        List<Field> fields = new ArrayList<>(size);
        Connection connection = DriverManager.getConnection(config.command(commands[0]), config.command(commands[1]), config.command(commands[2]));
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM Entry");
        while (result.next()) {
            fields.add(new Field(result.getInt(1)));
        }
        StoreXML storeXML = new StoreXML(source);
        storeXML.save(fields);
        ConvertXSQT convert = new ConvertXSQT();
        convert.convert(source, dest, new File("schema.xml"));
        Queue<String> queue = new ParseXSQT().parse(dest);
        String number = queue.poll();
        int results = 0;
        int index = 1;
        int except = 0;
        while (number != null) {
            results += Integer.valueOf(number);
            number = queue.poll();
            except += index++;
        }
        System.out.println(except);
        System.out.println(index);
        System.out.println(results);*/
    }
}