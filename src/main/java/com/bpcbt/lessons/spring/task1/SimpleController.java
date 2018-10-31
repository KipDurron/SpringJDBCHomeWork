package com.bpcbt.lessons.spring.task1;

import com.bpcbt.lessons.spring.task1.ObjectMapping.*;
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
        transfer("Dmitry", "Vasily", 25000L, "RUR");
        printAllDataFromDatabase();
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
       Account account = jdbcTemplate.queryForObject("select accounts.id, account_number, currency, amount" +
                       " from customers join accounts on customers.account_id = accounts.id  where customers.name = ?",
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

    private int updateAmount(int id, int newAmount){
       return jdbcTemplate.update("update accounts SET amount=? WHERE id=?", newAmount, id);
    }

    private void transfer(String customerFrom, String customerTo, Long amount, String currency) {
        Account accountFrom = getCustomerAccount(customerFrom);
        Double multiplierFrom = getCurrencyMultiplier(currency, accountFrom.getCurrency());
        int newAmountFrom = (int)(accountFrom.getAmount() - amount*multiplierFrom);
        if (newAmountFrom >= 0){
            Account accountTo = getCustomerAccount(customerTo);
            System.out.println("transfer of " + amount + " " + currency + " FROM "+ customerFrom +
                    " with account_id = " + accountFrom.getId()+
            " TO " + customerTo + " with account_id = " + accountTo.getId());
            System.out.println("BEFORE:");
            System.out.println(getCustomerAccount(customerFrom));
            System.out.println(getCustomerAccount(customerTo));
            updateAmount(accountFrom.getId(), newAmountFrom);
            Double multiplierTo = getCurrencyMultiplier(currency, accountTo.getCurrency());
            int newAmountTo = (int)(accountTo.getAmount() + amount*multiplierTo);
            updateAmount(accountTo.getId(), newAmountTo);
            System.out.println("AFTER:");
            System.out.println(getCustomerAccount(customerFrom));
            System.out.println(getCustomerAccount(customerTo));
        } else {
            System.out.print("Недостаточно средств для перевода денег");
        }
    }

    private void printAllDataFromDatabase() {
        printAllAccount();
        printAllCustomers();
        printAllCurrencyRates();
    }



    public  List<Account> printAllAccount(){
        List<Account> accounts = jdbcTemplate.query(
                "select * from accounts",
                new AccountMapper());

        return accounts;
//        System.out.println("----------------");
//
//        for (Account account : accounts) {
//            System.out.println(account);
//        }
    }

    private  void printAllCustomers(){
        List<Customer> customers = jdbcTemplate.query(
                "select * from customers",
               new CustomerMapper());

        System.out.println("----------------");

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private  void printAllCurrencyRates(){
        List<CurrencyRates> currencyRates = jdbcTemplate.query(
                "select * from currency_rates",
                new CurrencyRatesMapper());

        System.out.println("----------------");

        for (CurrencyRates currencyRate: currencyRates) {
            System.out.println(currencyRate);
        }
    }


}
