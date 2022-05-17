import db.AccountRepository;
import db.impl.PostgresAccountRepository;
import entity.AccountEntity;

import javax.swing.*;

public class Main {
    static AccountRepository accountRepository = new PostgresAccountRepository();
    public static void main(String[] args) {
        String accountName = JOptionPane.showInputDialog("Введите ваше имя:");
        AccountEntity workAccount = accountRepository.getByName(accountName);
        if (workAccount==null) {
            int balance = Integer.parseInt(JOptionPane.showInputDialog("Введите баланс:"));

            AccountEntity account = new AccountEntity().
                    setName(accountName).
                    setValue(balance);

            accountRepository.addAccount(account);
            System.out.println("В базу данных добавлен пользователь с именем: " + accountName);
        }else {
            System.out.println("В базе данных уже есть пользователь с таким именем");
        }
    }
}
