package com.bpcbt.lessons.spring.task1.ObjectMapping;

public class Customer {
    private int id;
    private String name;
    private int account_id;

    public Customer(int id, String name, int account_id) {
        this.id = id;
        this.name = name;
        this.account_id = account_id;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, name='%s', account_id='%s']",
                id, name, account_id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public int getAccount_id() {
        return account_id;
    }
}
