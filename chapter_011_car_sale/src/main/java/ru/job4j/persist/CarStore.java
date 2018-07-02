package ru.job4j.persist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.Car;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class CarStore implements Closeable {

    private final static CarStore instance = new CarStore();

    private SessionFactory factory;

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
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
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
                    return session.createQuery("from Car").list();
                }
        );
    }

    public Car findByID(final String id) {
        return this.tx(
                session -> {
                    final Query query = session.createQuery("from Item where id =" + id);
                    return (Car) query.list().get(0);
                }
        );
    }

    @Override
    public void close() throws IOException {
        this.factory.close();
    }
}
