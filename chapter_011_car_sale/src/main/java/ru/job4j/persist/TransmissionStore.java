package ru.job4j.persist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.Bodywork;
import ru.job4j.Car;
import ru.job4j.Motor;
import ru.job4j.Transmission;

import java.util.function.Function;

public class TransmissionStore {

    private final static TransmissionStore instance = new TransmissionStore();

    private final SessionFactory factory;

    public static TransmissionStore getInstance() {
        return instance;
    }

    private TransmissionStore() {
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

    public void add(final Transmission transmission) {
        this.tx(
                session -> {
                    session.save(transmission);
                    return null;
                }
        );
    }

    public void update(final Transmission transmission) {
        this.tx(
                session -> {
                    session.update(transmission);
                    return null;
                }
        );
    }

    public void delete(final Transmission transmission) {
        this.tx(
                session -> {
                    session.delete(transmission);
                    return null;
                }
        );
    }

    public Transmission findByID(Integer id) {
        return this.tx(
                session -> {
                    return session.get(Transmission.class, id);
                }
        );
    }

    public void close() {
        this.factory.close();
    }
}