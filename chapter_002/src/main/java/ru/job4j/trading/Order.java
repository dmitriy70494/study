package ru.job4j.trading;

/**
 * класс Order. Заявка для "Стакана"
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 23.05.2018
 */
public class Order {

    /**
     * id - уникальный ключ заявки.
     */
    private final int id;

    /**
     * book - идентификатор ценной бумаги.
     */
    private final int book;

    /**
     * add(true)/delete(false) - выставить заявку на торги или снять
     */
    private final boolean type;

    /**
     * bid(покупка true)/ask(продажа false) - заявка имеет два действия. Заявка на покупка ценной бумаги или на продажу.
     */
    private final boolean action;

    /**
     * цена, по которой мы ходим сделать действия покупки или продажи.
     */
    private final double price;

    /**
     * количество акций, которые мы хотим продать или купить.
     */
    private int volume;

    public Order(int id, int book, boolean type, boolean action, double price, int volume) {
        this.id = id;
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.volume = volume;
    }

    public int getId() {
        return this.id;
    }

    public int getBook() {
        return this.book;
    }

    public boolean isType() {
        return type;
    }

    public boolean isAction() {
        return action;
    }

    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

}
