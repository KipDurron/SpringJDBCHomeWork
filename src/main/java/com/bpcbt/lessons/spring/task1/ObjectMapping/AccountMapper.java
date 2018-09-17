package com.bpcbt.lessons.spring.task1.ObjectMapping;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Customer customer = new Customer(resultSet.getInt("id"),
                resultSet.getString("name"), resultSet.getInt("account_id"));

        Account account = new Account(resultSet.getInt("account_id"),
                resultSet.getInt("account_number"),
                resultSet.getString("currency"),
                resultSet.getInt("amount"),
                customer);

        return account;
    }
}
