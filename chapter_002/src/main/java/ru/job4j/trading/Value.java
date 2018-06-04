package ru.job4j.trading;

import java.util.TreeMap;

/**
 * Хранит 2 упорядоченных дерева заявок purches - заявки на покупку и sales заявки на продажу
 */
public class Value {

    /**
     * Хранит покупки
     */
    private TreeMap<KeyOrder, Order> purches = new TreeMap<KeyOrder, Order>();

    /**
     * Хранит продажи
     */
    private TreeMap<KeyOrder, Order> sales = new TreeMap<KeyOrder, Order>();

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
    boolean addOrder(Order order) {
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
    boolean deleteOrder(Order order) {
        boolean access;
        if (order.isAction()) {
            access = purches.remove(new KeyOrder(order)) != null;
        } else {
            access = sales.remove(new KeyOrder(order)) != null;
        }
        return access;
    }

    TreeMap<KeyOrder, Order> getPurches() {
        return purches;
    }

    TreeMap<KeyOrder, Order> getSales() {
        return sales;
    }
}
