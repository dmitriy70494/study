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
     * Внутренний класс экземпляры которого используются как ключи TreeMap дерева
     */
    private class KeyOrder implements Comparable<KeyOrder> {

        /**
         * совпадает с id заявки
         */
        int id;

        /**
         * цена заявки
         */
        double price;


        /**
         * Конструктор для ключа, для быстрого вычисления хешфункции, удобной навигвции и дополнительных полей и сортировки
         * все заявки будут храниться в 2 упорядоченном деревьях.
         *
         * @param order
         */
        public KeyOrder(Order order) {
            if (order != null) {
                this.id = order.getId();
                this.price = order.getPrice();
            }
        }

        /**
         * Проверяет все поля ключа на равенство
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o instanceof KeyOrder) {
                return false;
            }
            KeyOrder keyOrder = (KeyOrder) o;
            return id == keyOrder.id && Double.compare(keyOrder.price, price) == 0;
        }

        /**
         * Определяет хэшкод ключа
         * @return
         */
        @Override
        public int hashCode() {
            long doubl = Double.doubleToLongBits(price);
            return 31 * (31 * (31 * 19) + id) + (int) (doubl - (doubl >>> 32));
        }

        /**
         * Данный компаратор немного отличается от метода equals и естесственной сортировки, он сортирует по убыванию
         * цены, но если цены равны то по увеличению id заявки.
         * @param order
         * @return
         */
        @Override
        public int compareTo(KeyOrder order) {
            int result = (int) (order.price - price);
            if (result == 0) {
                result = id - order.id;
            }
            return result;
        }
    }

    /**
     * Хранит 2 упорядоченных дерева заявок purches - заявки на покупку и sales заявки на продажу
     */
    private class Value {
        TreeMap<KeyOrder, Order> purches;
        TreeMap<KeyOrder, Order> sales;

        /**
         * Инициирует деревья
         */
        public Value() {
            purches = new TreeMap<KeyOrder, Order>();
            sales = new TreeMap<KeyOrder, Order>();
        }

        /**
         * Данный метод проверяет после добавления в дерево нового элемента. Берет начальную заявку(готовы купить по самой высокой цене) на покупку
         * и конечную (готовы продать по самой низкой цене) заявку на продажу. Если они не равны нулл, то есть существуют,
         * мы проверяем выше ли цена покупки, цены продажи акций. Если да, то вычисляем разницу, присваиваем значение количества акций
         * проверяем, если акций меньше или равно 0 в заявке то удаляем ее и передаем системе следующий элемент для проверки.
         * Удаляем все ссылки, чтобы система смогла удалить объекты
         *
         */
        private void checkOrders() {
            Order purch = purches.firstEntry().getValue();
            Order sale = sales.lastEntry().getValue();
            while (purch != null && sale != null && purch.getPrice() >= sale.getPrice()) {
                int newVolume = purch.getVolume() - sale.getVolume();
                purch.setVolume(newVolume);
                sale.setVolume(-newVolume);
                if (purch.getVolume() <= 0) {
                    purches.remove(new KeyOrder(purch));
                }
                if (sale.getVolume() <= 0) {
                    sales.remove(new KeyOrder(sale));
                }
                purch = null;
                sale = null;
                if (purches.size() > 0 && sales.size() > 0) {
                    purch = purches.firstEntry().getValue();
                    sale = sales.lastEntry().getValue();
                }
            }
        }


        /**
         * Добавляет заявку в то дерева, на которое указывает boolean Action заявки true заявки на покупку, false на продажу
         *
         * @param order заявка
         * @return boolean удален ли объект
         */
        private boolean addOrder(Order order) {
            boolean access;
            if (order.isAction()) {
                access = purches.put(new KeyOrder(order), order) == null;
            } else {
                access = sales.put(new KeyOrder(order), order) == null;
            }
            if (purches.size() > 0 && sales.size() > 0) {
                this.checkOrders();
            }
            return access;
        }

        /**
         * Удаляет заявку из того дерева, на которое указывает boolean Action заявки true заявки на покупку, false на продажу
         * @param order
         * @return
         */
        private boolean deleteOrder(Order order) {
            boolean access;
            if (order.isAction()) {
                access = purches.remove(new KeyOrder(order)) != null;
            } else {
                access = sales.remove(new KeyOrder(order)) != null;
            }
            return access;
        }
    }


    /**
     * Все стаканы Integer идентификатор стакана int book указанной в заявке
     */
    private HashMap<Integer, Value> doms;

    public DepthOfMarkets() {
        doms = new HashMap<Integer, Value>(100);
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
            Iterator it = orders.sales.values().iterator();
            buffer.append(String.format("%10s%10s%10s%n", "Покупка", "Цена", "Продажа"));
            this.printNext(it, false, buffer);
            it = orders.purches.values().iterator();
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
