package ru.job4j.trading;

/**
 * Класс экземпляры которого используются как ключи TreeMap дерева
 */
public class KeyOrder implements Comparable<KeyOrder> {

    /**
     * совпадает с id заявки
     */
    private int id;

    /**
     * цена заявки
     */
    private double price;


    /**
     * Конструктор для ключа, для быстрого вычисления хешфункции, удобной навигвции и дополнительных полей и сортировки
     * все заявки будут храниться в 2 упорядоченном деревьях.
     *
     * @param order
     */
    KeyOrder(Order order) {
        if (order != null) {
            this.id = order.getId();
            this.price = order.getPrice();
        }
    }

    /**
     * Проверяет все поля ключа на равенство
     *
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
     *
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
     *
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
