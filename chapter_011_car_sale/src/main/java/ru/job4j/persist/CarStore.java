package ru.job4j.persist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.Car;
import ru.job4j.User;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class CarStore implements Closeable {

    private final static CarStore instance = new CarStore();

    private final SessionFactory factory;

    private Session session;

    private Transaction transaction;

    private String table;

    private CarStore() {
        this.factory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    public static CarStore getInstance() {
        return instance;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = this.factory.openSession();
        final Transaction txs = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            txs.commit();
            session.close();
        }
    }

    public void add(final Car car) {
        this.tx(
                session -> {
                    session.save(car);
                    return null;
                }
        );
    }

    public void update(final Car car) {
        this.tx(
                session -> {
                    session.update(car);
                    return null;
                }
        );
    }

    public void delete(final Car car) {
        this.tx(
                session -> {
                    session.delete(car);
                    return null;
                }
        );
    }

    public List findAll() {
        return this.tx(
                session -> {
                    return session.createQuery("from Car where done = 'true'").list();
                }
        );
    }

    public List<List> findAllPartsCar() {
        List<List> list = new LinkedList<>();
        list.add(this.tx(session -> {
            return session.createQuery("from Motor").list();
        }));
        list.add(this.tx(session -> {
            return session.createQuery("from Transmission").list();
        }));
        list.add(this.tx(session -> {
            return session.createQuery("from Bodywork").list();
        }));
        return list;
    }

    public Car findByID(Integer id) {
        this.session = this.factory.openSession();
        this.transaction = session.beginTransaction();
        try {
            Car car = session.get(Car.class, id);
            return car;
        } catch (final Exception e) {
            this.session.getTransaction().rollback();
            this.closeSession();
            throw e;
        }
    }

    /**
     * Сервер не использует почему то обновленный файл, он использует только тот файл html, который скомпилировал перед этим,
     * так что этот способ не подходит, пока не выяснится, как это все работает,
     * для скорости можно создавать текстовый файл и передавать его в jsp на динамическую прорисовку; файл в таргете тоже менял, не помогло.
     */
    public void drawTableHTML() {
        StringBuilder result = new StringBuilder("<tr><th>Фото</th><th>Название</th><th>Тип двигателя</th><th>Передача</th><th>Кузов</th><th>Дата создания</th></tr>");
        for (Object obj : this.findAll()) {
            Car car = (Car) obj;
            result.append("<tr><td>")
                    .append(car.getFoto()).append("</td><td>")
                    .append(car.getName()).append("</td><td>")
                    .append(car.getMotor().getName()).append("</td><td>")
                    .append(car.getTransmission().getName()).append("</td><td>")
                    .append(car.getBodywork().getName()).append("</td><td>")
                    .append(car.getCreate())
                    .append("</td></tr>");
        }
        this.table = result.toString();
    }

    public User findUser(String login, String password) {
        return this.tx(
                session -> {
                    User user = null;
                    Query query = session.createQuery("from User where login = :login and password = :password");
                    query.setParameter("login", login);
                    query.setParameter("password", password);
                    List list = query.list();
                    if (list.size() != 0) {
                        user = (User) list.get(0);
                    }
                    return user;
                }
        );
    }

    public List findCarByUser(Integer userId) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("from Car where id_user = :user");
                    query.setParameter("user", userId);
                    return query.list();
                }
        );

    }

    public void closeSession() {
        if (this.session != null) {
            this.session.close();
        }
    }

    public void commitTransaction() {
        if (this.transaction != null) {
            this.transaction.commit();
        }
    }

    @Override
    public void close() throws IOException {
        this.commitTransaction();
        this.closeSession();
        this.factory.close();
    }

    public String getTable() {
        return this.table;
    }
}
