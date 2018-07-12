package ru.job4j.persist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.Car;
import ru.job4j.Motor;

import java.util.function.Function;

public class MotorStore {

    private final static MotorStore instance = new MotorStore();

    private final SessionFactory factory;

    public static MotorStore getInstance() {
        return instance;
    }

    private MotorStore() {
        this.factory = new Configuration()
                .configure()
                .buildSessionFactory();
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

    public void add(final Motor motor) {
        this.tx(
                session -> {
                    session.save(motor);
                    return null;
                }
        );
    }

    public void update(final Motor motor) {
        this.tx(
                session -> {
                    session.update(motor);
                    return null;
                }
        );
    }

    public void delete(final Motor motor) {
        this.tx(
                session -> {
                    session.delete(motor);
                    return null;
                }
        );
    }

    public Motor findByID(Integer id) {
        return this.tx(
                session -> {
                    return session.get(Motor.class, id);
                }
        );
    }

    public void close() {
        this.factory.close();
    }
}
