package db;

import entity.AccountEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountEntityRowMapper implements RowMapper<AccountEntity> {
    @Override
    public AccountEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AccountEntity()
                .setId(rs.getInt("id"))
                .setValue(rs.getInt("value"))
                .setName(rs.getString("name"));
    }
}
