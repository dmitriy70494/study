package ru.job4j.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.item.Item;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

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

    public void add(Item item) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Item item) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Item item) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    public List<Item> findAll() {
        Session session = this.factory.openSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        return items;
    }

    public Item findByID(String id) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        Item item = (Item) session.createQuery("from Item where id = " + id).list().get(0);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public void close() throws IOException {
        this.factory.close();
    }
}
