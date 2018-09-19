package com.bpcbt.lessons.spring.task1.ObjectMapping;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {

        Account account = new Account(resultSet.getInt("id"),
                resultSet.getInt("account_number"),
                resultSet.getString("currency"),
                resultSet.getInt("amount"));

        return account;
    }
}
