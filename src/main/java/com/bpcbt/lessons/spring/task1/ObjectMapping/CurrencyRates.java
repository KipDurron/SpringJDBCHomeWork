package com.bpcbt.lessons.spring.task1.ObjectMapping;

public class CurrencyRates {
    private int id;
    private String currency1;
    private String currency2;
    private Double multiplier;

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrency1(String currency1) {
        this.currency1 = currency1;
    }

    public void setCurrency2(String currency2) {
        this.currency2 = currency2;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }

    public int getId() {

        return id;
    }

    public String getCurrency1() {
        return currency1;
    }

    public String getCurrency2() {
        return currency2;
    }

    public Double getMultiplier() {
        return multiplier;
    }

    public CurrencyRates(int id, String currency1, String currency2, Double multiplier) {

        this.id = id;
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.multiplier = multiplier;
    }

    @Override
    public String toString() {
        return String.format(
                "CurrencyRates[id='%d', currency1='%s', currency2='%s', multiplier='%f']",
                id, currency1, currency2, multiplier);
    }
}
