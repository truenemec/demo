package com.demo.productservice.currency;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CurrencyService {

    BigDecimal convert(LocalDate date, String currencyFrom, String currencyTo, BigDecimal value);

    String defaultCurrency();
}
