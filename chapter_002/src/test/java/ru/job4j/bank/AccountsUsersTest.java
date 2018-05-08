package ru.job4j.bank;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса AccountsUsers. Проверка добавления - удаления пользователей, списание со счета на другой счет.
 *
 *
 * @author Dmitriy Balandin (d89086362742@yandex.ru)
 * @version $Id$
 * @since 08.05.2018
 */
public class AccountsUsersTest {
    AccountsUsers allAccountsUsers = new AccountsUsers();
    User user1;
    User user2;
    String allert;

    @Before
    public void loadOutput() {
       this.user1 = new User("Дима", "00 00 000000");
        this.user2 = new User("Дима", "10 00 000000");
        try {
            this.allAccountsUsers.addUser(this.user1);
            this.allAccountsUsers.addUser(this.user2);
            Account account1 = new Account("1");
            account1.setValue(new BigDecimal("7001"));
            Account account2 = new Account("2");
            account2.setValue(new BigDecimal("11009"));
            this.allAccountsUsers.addAccountToUser("00 00 000000", account1);
            this.allAccountsUsers.addAccountToUser("10 00 000000", account2);
        } catch(ExistingUserException eue) {
            allert = eue.getMessage();
        }
    }

    @Test
    public void whenInAccountsUsersAddNewUser() {
        User user3 = new User("Дима", "00 00 000000");
        try {
            this.allAccountsUsers.addUser(user3);
        } catch(ExistingUserException eue) {
            System.out.println(eue.getMessage());
        }
        assertThat(allAccountsUsers.getUsers().containsKey(user2) , is(true));
    }

    @Test
    public void whenInAccountsUsersAddUserExistingInSystem() {
        User user2 = new User("Дима", "00 00 000000");
        try {
            this.allAccountsUsers.addUser(user2);
        } catch(ExistingUserException eue) {
            allert = eue.getMessage();
        }
        assertThat(allert , is("Такой пользователь уже существует в списке"));
    }

    @Test
    public void whenInAccountsUsersDeleteUser() {
        try {
            this.allAccountsUsers.deleteUser(user1);
            this.allAccountsUsers.deleteUser(user2);
        } catch(ExistingUserException eue) {
            System.out.println(eue.getMessage());
        }
        assertThat(this.allAccountsUsers.getUsers().size() , is(0));
    }

    @Test
    public void whenInAccountsUsersDeleteUserExistingInSystem() {
        User user3 = new User("Дима", "11 00 000000");
        try {
            this.allAccountsUsers.deleteUser(user3);
        } catch(ExistingUserException eue) {
            this.allert = eue.getMessage();
        }
        assertThat(this.allert , is("Не удалось удалить пользователя, так как он не существует в списке"));
    }

    @Test
    public void whenTransferUserToUserNotMoney() {
        assertThat(allAccountsUsers.transferMoney("00 00 000000", "1", "10 00 000000", "2", 8000.0), is(false));
    }

    @Test
    public void whenTransferUserToUserEnoughMoney() {
        allAccountsUsers.transferMoney("00 00 000000", "1", "10 00 000000", "2", 6000.0);
        boolean debiting = allAccountsUsers.getUserAccounts("00 00 000000").get(0).getValue().compareTo(new BigDecimal(1001)) == 0;
        boolean receipt = allAccountsUsers.getUserAccounts("10 00 000000").get(0).getValue().compareTo(new BigDecimal(17009)) == 0;
        assertThat(debiting && receipt , is(true));
    }
}
