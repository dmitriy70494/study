package ru.job4j.bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Класс содержит список всех пользователей и их счетов, а также методы работы с ними
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 07.05.2018
 */
public class AccountsUsers {

    /**
     * Список содержит по ключу пользователя, а по значению список его банковских счетов
     */
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * Добавление пользователя.
     *
     * @param user пользователь
     */
    public void addUser(User user) throws ExistingUserException {
        if (this.users.putIfAbsent(user, new ArrayList<Account>()) != null) {
            throw new ExistingUserException("Такой пользователь уже существует в списке");
        }
    }

    /**
     * Удаление пользователя.
     *
     * @param user пользователь
     */
    public void deleteUser(User user) throws ExistingUserException {
        if (this.users.remove(user) == null) {
            throw new ExistingUserException("Не удалось удалить пользователя, так как он не существует в списке");
        }
    }

    /**
     * Добавить счёт пользователю.
     *
     * @param passport паспортные данные
     * @param account  счет пользователя
     */
    public void addAccountToUser(String passport, Account account) throws ExistingUserException {
        User user = new User("", passport);
        if (this.users.containsKey(user)) {
            this.users.get(user).add(account);
        } else {
            throw new ExistingUserException("Не удалось добавить счет, так как такого пользователя не существует в списке. - Проверьте паспортные данные либо введите пользователя в систему");
        }
    }

    /**
     * Удалить один счёт пользователя.
     *
     * @param passport паспортные данные
     * @param account  счет пользователя
     */
    public void deleteAccountFromUser(String passport, Account account) throws ExistingUserException {
        List<Account> accounts = this.users.get(new User("", passport));
        if (accounts != null && accounts.remove(account)) {
            System.out.println("Счет" + passport + "удален");
        } else {
            throw new ExistingUserException("Не удалось удалить счет пользователя не существует такого счета или пользователя");
        }
    }

    /**
     * Получить список счетов для пользователя.
     *
     * @param passport паспортные данные
     * @return список счетов
     */
    public List<Account> getUserAccounts(String passport) {
        return this.users.get(new User("", passport));
    }

    /**
     * Метод для перечисления денег с одного счёта на другой счёт:
     * если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят) должен вернуть false.
     * добавляем часто встречающиеся переменные
     * сначала получаем списки счетов пользователей
     * узнаем индексы в списке по которым совершается операция
     * проверяем есть ли такие счета и пользователи
     * если есть присваиваем значения этих пользователей и проверяем достаточно ли средств на счете
     * если достаточно, вычитаем double amount из первого счета и округляем число до 2 цифр после знака (возможно это избыточно)
     * и прибавляем double amount ко второму счету и округляем число до 2 единиц после целого числа.
     *
     * @param srcPassport  паспортные данные пользователя переводящего денежные средства
     * @param srcRequisite реквизиты счета плательщика
     * @param destPassport паспортные данные пользователя получающего денежные средства
     * @param destRequisite реквизиты счета получателя платежа
     * @param amount       сумма платежа
     * @return true - платеж прошел успешно, false - платеж не прошел
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) {
        BigDecimal amountValue = new BigDecimal(String.valueOf(amount));
        BigDecimal srcValue = null;
        boolean success;
        List<Account> srcList = this.getUserAccounts(srcPassport);
        List<Account> destList = this.getUserAccounts(destPassport);
        int src = srcList.indexOf(new Account(srcRequisite));
        int dest = destList.indexOf(new Account(destRequisite));
        success = src != -1 && dest != -1;
        if (success) {
            srcValue = srcList.get(src).getValue();
            success = srcValue.compareTo(amountValue) != -1;
        }
        if (success) {
            srcList.get(src).setValue(srcValue.subtract(amountValue).setScale(2, BigDecimal.ROUND_HALF_UP));
            destList.get(dest).setValue(destList.get(dest).getValue().add(amountValue).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        return success;
    }

    protected Map<User, List<Account>> getUsers() {
        return this.users;
    }
}
