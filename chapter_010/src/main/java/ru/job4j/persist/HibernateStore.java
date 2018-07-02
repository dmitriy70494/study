package ru.job4j.persist;

        import org.hibernate.Session;
        import org.hibernate.SessionFactory;
        import org.hibernate.Transaction;
        import org.hibernate.cfg.Configuration;
        import org.hibernate.query.Query;
        import ru.job4j.item.Item;

        import java.io.Closeable;
        import java.io.IOException;
        import java.util.List;
        import java.util.function.Function;

public class HibernateStore implements Closeable {

    private final static HibernateStore instance = new HibernateStore();

    private SessionFactory factory;

    private HibernateStore() {
        this.factory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    public static HibernateStore getInstance() {
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

    public void add(final Item item) {
        this.tx(
                session -> {
                    session.save(item);
                    return null;
                }
        );
    }

    public void update(Item item) {
        this.tx(
                session -> {
                    session.update(item);
                    return null;
                }
        );
    }

    public void delete(final Item item) {
        this.tx(
                session -> {
                    session.delete(item);
                    return null;
                }
        );
    }

    public List findAll() {
        return this.tx(
                session -> {
                    return session.createQuery("from Item").list();
                }
        );
    }

    public Item findByID(final String id) {
        return this.tx(
                session -> {
                    final Query query = session.createQuery("from Item where id =" + id);
                    return (Item) query.list().get(0);
                }
        );
    }

    @Override
    public void close() throws IOException {
        this.factory.close();
    }
}
