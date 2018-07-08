package ru.job4j.persist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.Bodywork;
import ru.job4j.Car;
import ru.job4j.Motor;

import java.util.function.Function;

public class BodyworkStore {

    private final static BodyworkStore instance = new BodyworkStore();

    private final SessionFactory factory;

    public static BodyworkStore getInstance() {
        return instance;
    }

    private BodyworkStore() {
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

    public void add(final Bodywork bodywork) {
        this.tx(
                session -> {
                    session.save(bodywork);
                    return null;
                }
        );
    }

    public void update(final Bodywork bodywork) {
        this.tx(
                session -> {
                    session.update(bodywork);
                    return null;
                }
        );
    }

    public void delete(final Bodywork bodywork) {
        this.tx(
                session -> {
                    session.delete(bodywork);
                    return null;
                }
        );
    }

    public Bodywork findByID(Integer id) {
        return this.tx(
                session -> {
                    return session.get(Bodywork.class, id);
                }
        );
    }

    public void close() {
        this.factory.close();
    }
}