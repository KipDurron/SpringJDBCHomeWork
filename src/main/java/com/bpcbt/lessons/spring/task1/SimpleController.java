package com.bpcbt.lessons.spring.task1;

import com.bpcbt.lessons.spring.task1.ObjectMapping.Account;
import com.bpcbt.lessons.spring.task1.ObjectMapping.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleController {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SimpleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void run() {
        select();
        getCustomerAccount("Vasily");
    }


    private void select() {
        jdbcTemplate.query("select * from customers where id between ? and ?",
                preparedStatement -> {
                    preparedStatement.setInt(1, 1);
                    preparedStatement.setInt(2, 2);
                },
                resultSet -> {
                    System.out.println(resultSet.getString("name"));
                }
        );
    }

    private Account getCustomerAccount(String name) {
        //TODO get customer account by name
       List<Account> results = jdbcTemplate.query("select * from customers join accounts on customers.account_id = accounts.id  where customers.name = ?",
                preparedStatement -> {
                    preparedStatement.setString(1, name);
                },
                new AccountMapper()
        );
        for (Account account : results) {
            System.out.println(account);
        }
        return null;
    }

    private void transfer(String customerFrom, String customerTo, Long amount, String currency) {

    }

    private void printAllDataFromDatabase() {

    }

    //TODO Create table currency_rates(Curr1, curr2, multiplier)
    //TODO Write controller that will allow to transfer money between customers
    //TODO with currency conversion by customers names and persist in database

}
