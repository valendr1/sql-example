package db;

import entity.AccountEntity;

import java.util.List;

public interface AccountRepository {

    List<AccountEntity> getAll();
    AccountEntity getByName(String accountName);
    void addAccount(AccountEntity account);
}
