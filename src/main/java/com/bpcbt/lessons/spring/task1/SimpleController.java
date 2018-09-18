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
        transfer("Ilya", "Vasily", 100000L, "RUR");
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
       Account account = jdbcTemplate.queryForObject("select * from customers join accounts on customers.account_id = accounts.id  where customers.name = ?",
                new AccountMapper(),
               name
        );
        return account;
    }

    private Double getCurrencyMultiplier(String currencyFrom, String currencyTo) {
        Double multiplier = jdbcTemplate.queryForObject("select multiplier from currency_rates where currency1 = ? and currency2 = ?",
                Double.class,
                currencyFrom, currencyTo
        );
        return multiplier;
    }

    private void updateAmount(int id, int newAmount){
        jdbcTemplate.update("update accounts SET amount=? WHERE id=?", newAmount, id);
    }

    private void transfer(String customerFrom, String customerTo, Long amount, String currency) {
        Account accountFrom = getCustomerAccount(customerFrom);
        Double multiplierFrom = getCurrencyMultiplier(currency, accountFrom.getCurrency());
        int newAmountFrom = (int)(accountFrom.getAmount() - amount*multiplierFrom);
        if (newAmountFrom >= 0){
            updateAmount(accountFrom.getId(), newAmountFrom);
            Account accountTo = getCustomerAccount(customerTo);
            Double multiplierTo = getCurrencyMultiplier(currency, accountTo.getCurrency());
            int newAmountTo = (int)(accountTo.getAmount() + amount*multiplierTo);
            updateAmount(accountTo.getId(), newAmountTo);
            System.out.println(getCustomerAccount(customerFrom));
            System.out.println(getCustomerAccount(customerTo));
        } else {
            System.out.print("Недостаточно средств для перевода денег");
        }
    }

    private void printAllDataFromDatabase() {

    }

    //TODO Create table currency_rates(Curr1, curr2, multiplier)
    //TODO Write controller that will allow to transfer money between customers
    //TODO with currency conversion by customers names and persist in database

}
