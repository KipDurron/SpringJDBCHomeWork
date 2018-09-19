package com.bpcbt.lessons.spring.task1.ObjectMapping;

public class Account {
    private int id;
    private int account_number;
    private String currency;
    private int amount;

    public Account(int id, int account_number, String currency, int amount) {

        this.id = id;
        this.account_number = account_number;
        this.currency = currency;
        this.amount = amount;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {

        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public int getAmount() {
        return amount;
    }

    public int getAccount_number() {

        return account_number;
    }

    @Override
    public String toString() {
        return String.format(
                "Account[id='%d', account_number='%d', currency='%s', amount='%s']",
                id, account_number, currency, amount);
    }
}
