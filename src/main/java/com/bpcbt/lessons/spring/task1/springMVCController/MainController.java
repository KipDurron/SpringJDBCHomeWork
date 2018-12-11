package com.bpcbt.lessons.spring.task1.springMVCController;

import com.bpcbt.lessons.spring.task1.ObjectMapping.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class MainController {
    private JdbcTemplate jdbcTemplate;

    private enum Currency {
        RUB, USD, EUR
    }

    @Autowired
    public MainController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showAllAccount() {
        List<Customer> customers = jdbcTemplate.query(
                "select * from customers",
                new CustomerMapper());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customerList", customers);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/customer/{id}")
    public @ResponseBody
    ModelAndView getCustomer(@PathVariable(value="id") int id) {
        String nameCustomer = jdbcTemplate.queryForObject("select customers.name" +
                        " from customers where customers.id = ?", String.class,id);

        List<Account> accounts = jdbcTemplate.query("select accounts.id, account_number, currency, amount" +
                        " from customers join accounts on customers.account_id = accounts.account_number  where customers.id = ?",
                new AccountMapper(),
                id
        );

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accountList", accounts);
        modelAndView.addObject("nameCustomer", nameCustomer);
        modelAndView.addObject("transaction", new Transaction());
        modelAndView.setViewName("customer");
        return modelAndView;
    }

    @RequestMapping(value = "/customer/sendTransaction", method = RequestMethod.POST)
    public ModelAndView addStudent(@Valid @ModelAttribute("transaction")Transaction transaction,
                                   BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return getError();
        }
        try {
            transfer(transaction.getAccountFrom(), transaction.getAccountTo(), transaction.getAmount(),
                    transaction.getCurrency());
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return getError();
        }

        return getCustomer(getCustomerId(transaction.getAccountFrom()));
    }

    @RequestMapping("/error")
    public @ResponseBody
    ModelAndView getError() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }


    private void transfer(int idAccountFrom, int idAccountTo, Long amount, String currency) {
        Account accountFrom = getCustomerAccount(idAccountFrom);
        Double multiplierFrom = getCurrencyMultiplier(currency, accountFrom.getCurrency());
        int newAmountFrom = (int)(accountFrom.getAmount() - amount*multiplierFrom);
        if (newAmountFrom >= 0){
            Account accountTo = getCustomerAccount(idAccountTo);
            updateAmount(accountFrom.getId(), newAmountFrom);
            Double multiplierTo = getCurrencyMultiplier(currency, accountTo.getCurrency());
            int newAmountTo = (int)(accountTo.getAmount() + amount*multiplierTo);
            updateAmount(accountTo.getId(), newAmountTo);
        }
    }



    public Account getCustomerAccount(int id) {
        Account account = jdbcTemplate.queryForObject("select accounts.id, account_number, currency, amount" +
                        " from accounts  where accounts.id = ?",
                new AccountMapper(),
                id
        );
        return account;
    }

    public Double getCurrencyMultiplier(String currencyFrom, String currencyTo) {
        Double multiplier = jdbcTemplate.queryForObject("select multiplier from currency_rates where currency1 = ? and currency2 = ?",
                Double.class,
                currencyFrom, currencyTo
        );
        return multiplier;
    }

    public int updateAmount(int id, int newAmount){
        return jdbcTemplate.update("update accounts SET amount=? WHERE id=?", newAmount, id);
    }

    public int getCustomerId(int id) {
        return jdbcTemplate.queryForObject("select customers.id" +
                " from customers join accounts on customers.account_id = accounts.account_number  where accounts.id = ?",
                Integer.class,
                id
        );
    }


}
