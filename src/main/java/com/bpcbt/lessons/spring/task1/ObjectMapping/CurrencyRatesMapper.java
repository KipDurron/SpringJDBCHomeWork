package com.bpcbt.lessons.spring.task1.ObjectMapping;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyRatesMapper implements RowMapper<CurrencyRates> {

    @Override
    public CurrencyRates mapRow(ResultSet resultSet, int i) throws SQLException {

        CurrencyRates currencyRate = new CurrencyRates(resultSet.getInt("id"),
                resultSet.getString("currency1"),
                resultSet.getString("currency2"),
                resultSet.getDouble("multiplier"));

        return currencyRate;
    }
}
