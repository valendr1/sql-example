package service;

import data.Category;
import db.AccountRepository;
import db.SpendRepository;
import db.impl.PostgresAccountRepository;
import db.impl.PostgresSpendRepository;
import entity.AccountEntity;
import entity.SpendEntity;

import javax.swing.*;
import java.util.Arrays;

public class SpendService {
    private SpendRepository spendRepository = new PostgresSpendRepository();
    private AccountRepository accountRepository = new PostgresAccountRepository();

    public void doSpend(AccountEntity account) {
        int index = JOptionPane.showOptionDialog(
                null,
                "Категория",
                "Выберите категорию траты",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                Arrays.stream(Category.values()).map(Category::getDescription).toArray(String[]::new),
                Category.SPORT.getDescription()
        );

        Category selected = Category.values()[index];
        long spendValue = Long.parseUnsignedLong(
                JOptionPane.showInputDialog("Введите размер траты")
        );
        String desc = JOptionPane.showInputDialog("Введите описание траты");
        if (isSpendAcceptedForGivenUser(account, spendValue)) {
            SpendEntity spend = new SpendEntity()
                    .setSpend(spendValue)
                    .setSpendCategory(selected)
                    .setAccountId(account.getId())
                    .setDescription(desc);

            spendRepository.addSpend(spend);
            account.setValue(account.getValue() - spendValue);
            accountRepository.updateAccount(account);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Невозможжно совершить списание",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showAllSpends(AccountEntity account) {
        Object[][] rows = spendRepository.getAllForAccount(account)
                .stream()
                .map(spend -> new Object[]{spend.getSpendCategory().getDescription(),
                        spend.getSpend(),
                        spend.getDescription()})
                .toArray(Object[][]::new);

        Object[] headers = {"Категория", "Размер траты", "Описание траты"};
        JTable table = new JTable(rows, headers);
        JOptionPane.showMessageDialog(null, new JScrollPane(table));
    }

    private boolean isSpendAcceptedForGivenUser(AccountEntity givenUser, long spend) {
        if (spend <= 0) {
            return false;
        }
        if (givenUser.getValue() < spend) {
            return false;
        }
        return true;
    }
}
