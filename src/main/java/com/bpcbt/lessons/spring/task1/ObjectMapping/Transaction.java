package com.bpcbt.lessons.spring.task1.ObjectMapping;

public class Transaction {
private Integer accountFrom;
private Integer accountTo;
private String currency;
private Long amount;


    public void setAccountFrom(Integer accountFrom) {
        this.accountFrom = accountFrom;
    }

    public void setAccountTo(Integer accountTo) {
        this.accountTo = accountTo;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getAccountFrom() {
        return accountFrom;
    }

    public Integer getAccountTo() {
        return accountTo;
    }

    public String getCurrency() {
        return currency;
    }

    public Long getAmount() {
        return amount;
    }
}
