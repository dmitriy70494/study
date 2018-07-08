package ru.job4j.persist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.*;

import java.util.function.Function;

public class UserStore {

    private final static UserStore instance = new UserStore();

    private final SessionFactory factory;

    public static UserStore getInstance() {
        return instance;
    }

    private UserStore() {
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

    public void add(final User user) {
        this.tx(
                session -> {
                    session.save(user);
                    return null;
                }
        );
    }

    public void update(final User user) {
        this.tx(
                session -> {
                    session.update(user);
                    return null;
                }
        );
    }

    public void delete(final User user) {
        this.tx(
                session -> {
                    session.delete(user);
                    return null;
                }
        );
    }

    public User findByID(Integer id) {
        return this.tx(
                session -> {
                    return session.get(User.class, id);
                }
        );
    }

    public void close() {
        this.factory.close();
    }
}