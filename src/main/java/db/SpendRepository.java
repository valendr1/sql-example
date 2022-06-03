package db;

import entity.AccountEntity;
import entity.SpendEntity;

import java.util.List;

public interface SpendRepository {
    List<SpendEntity> getAllForAccount(AccountEntity account);
    void addSpend(SpendEntity spend);

}
