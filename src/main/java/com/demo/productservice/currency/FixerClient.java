package com.demo.productservice.currency;

import com.demo.productservice.currency.model.CurrencyRate;

public interface FixerClient {

    CurrencyRate getCurrencyRates(String base);

}
