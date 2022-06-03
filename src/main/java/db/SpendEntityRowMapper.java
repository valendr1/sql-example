package db;

import data.Category;
import entity.SpendEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpendEntityRowMapper implements RowMapper<SpendEntity> {
    @Override
    public SpendEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SpendEntity().setDescription(rs.getString("description"))
                .setSpend(rs.getInt("spend"))
                .setAccountId(rs.getInt("account_id"))
                .setId(rs.getInt("id"))
                .setSpendCategory(Category.valueOf(rs.getString("spend_category")));
    }
}