package ru.job4j.trading;

import java.util.*;

/**
 * класс DepthOfMarkets. Создает хранилище для стаканов с заявками на покупку и продажу акций и автоматическим выявлением
 * проданных.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 23.05.2018
 */
public class DepthOfMarkets {

    /**
     * Все стаканы Integer идентификатор стакана int book указанной в заявке
     */
    private HashMap<Integer, Value> doms;

    /**
     * Конструктор
     */
    public DepthOfMarkets(int initialCapacity) {
        doms = new HashMap<Integer, Value>(initialCapacity);
    }


    /**
     * добавляет или удаляет заявку, в зависимости значения boolean type заявки true добавляет false удаляет.
     * При добавлении заявки мы добавляем экземпляр класса Value (внутренний класс, содержит 2 дерева TreeMap) по ключу.
     * Ключом является уникальный номер стакана book. При добавлении метод проверяет есть ли такой "Стакан" уже в HashMap
     * методом computeIfAbsent, если такого стакана нет, то создается новый стакан лямбда выражением и сразу в него
     * добавляется новая заявка. Вновь созданный стакан если не будет заявок не удаляется из системы, это необходимо
     * учесть и создать метод для удаления
     *
     * @param order
     * @return
     */
    public boolean processOrder(Order order) {
        boolean access = order != null;
        if (access && order.isType()) {
            access = doms.computeIfAbsent(order.getBook(), k -> new Value()).addOrder(order);
        } else if (access) {
            access = doms.get(order.getBook()).deleteOrder(order);
        }
        return access;
    }

    /**
     * Возвращает стринг с представлением всех заявок
     * @param book
     * @return
     */
    public String printDOM(int book) {
        StringBuffer buffer;
        Value orders = doms.get(book);
        if (orders != null) {
            buffer = new StringBuffer(1000);
            Iterator it = orders.getSales().values().iterator();
            buffer.append(String.format("%10s%10s%10s%n", "Покупка", "Цена", "Продажа"));
            this.printNext(it, false, buffer);
            it = orders.getPurches().values().iterator();
            this.printNext(it, true, buffer);
        } else {
            buffer = new StringBuffer("Стакана с таким именем не существует в системе");
        }
        return buffer.toString();
    }

    /**
     * Принимает итератор Списка и печатает список от начала
     * @param it
     * @param access
     * @param buffer
     */
    private void printNext(Iterator it, boolean access, StringBuffer buffer) {
        double price = 0;
        int volume = 0;
        Order order;
        if (it.hasNext()) {
            order = (Order) it.next();
            price = order.getPrice();
            volume = order.getVolume();
        }
        while (it.hasNext()) {
            order = (Order) it.next();
            if (price == order.getPrice()) {
                volume += order.getVolume();
            } else {
                print(volume, price, access, buffer);
                volume = order.getVolume();
                price = order.getPrice();
            }
        }
        if (price != 0) {
            print(volume, price, access, buffer);
        }
    }

    /**
     * форматирует и добавляет текст в Стринг, в зависимости от места, куда его нужно вставить.
     * @param volume
     * @param prevPrice
     * @param access
     * @param buffer
     */
    private void print(int volume, double prevPrice, boolean access, StringBuffer buffer) {
        if (access) {
            buffer.append(String.format("%10s%10s%10s%n", volume, prevPrice, ""));
        } else {
            buffer.append(String.format("%10s%10s%10s%n", "", prevPrice, volume));
        }
    }
}
