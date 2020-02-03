package com.demo.productservice.currency.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Builder
public class CurrencyRate {
    private Map<String, BigDecimal> currencyRate;
}
