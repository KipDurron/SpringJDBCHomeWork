package com.bpcbt.lessons.spring.task1.ObjectMapping;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {

        Customer customer = new Customer(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("account_id"));

        return customer;
    }
}
