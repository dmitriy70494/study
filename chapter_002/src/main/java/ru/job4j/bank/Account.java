package ru.job4j.bank;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Formatter;

/**
 * Класс содержит информацию о счете клиента банка.
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 07.05.2018
 */
public class Account {

    /**
     * Хранит в себе количество денег на счете
     */
    private volatile BigDecimal value;

    /**
     * Банковские реквизиты счета
     */
    private final String requisites;

    /**
     *
     */
    public Account(String requisites) {
        this.value = new BigDecimal("0");
        this.requisites = requisites;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        Account account = (Account)obj;
        return account != null ? this == account || this.requisites.equals(account.requisites) : false;
    }

    @Override
    public int hashCode() {
        return this.requisites.hashCode();
    }

    public static void main(String[] args) {
        Account s = new Account("");
        s.value = s.value.add(new BigDecimal("23.0984")).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(s.value);
    }
}
