package ru.job4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImportUser {



    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String login = null;
        String password = null;
        do {
            System.out.println("Введите стоп чтобы закончить программу");
            System.out.println("Введите имя пользователя");
            login = reader.readLine();
            if ("стоп".equals(login)) {
                break;
            }
            System.out.println("Введите пароль пользователя");
            password = reader.readLine();
            if ("стоп".equals(password)) {
                break;
            }
            storage.add(new User(0, login, password));
            System.out.println("Пользователь добавлен");
        } while(true);
    }
}
