package db.impl;

import db.AccountEntityRowMapper;
import db.AccountRepository;
import db.DataSourceProvider;
import entity.AccountEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class PostgresAccountRepository implements AccountRepository {
    private static final JdbcTemplate template =
            new JdbcTemplate(DataSourceProvider.INSTANCE.getDataSource());


    @Override
    public List<AccountEntity> getAll() {
        return template.query("SELECT * FROM account", new AccountEntityRowMapper());
    }

    @Override
    public AccountEntity getByName(String accountName) {
        try {
            return template.queryForObject("SELECT * FROM account WHERE name = ?",
                    new AccountEntityRowMapper(), accountName);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void addAccount(AccountEntity account) {
        template.update("INSERT INTO account (name , value) values(?, ?)",
                account.getName(), account.getValue());
    }

    @Override
    public void updateAccount(AccountEntity account) {
        template.update("UPDATE account SET name = ?, value = ? WHERE name = ?",
                account.getName(), account.getValue(), account.getName());
    }
}
