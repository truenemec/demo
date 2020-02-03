package com.demo.productservice.currency.impl;

import com.demo.productservice.currency.CurrencyService;
import com.demo.productservice.currency.FixerClient;
import com.demo.productservice.currency.model.CurrencyRate;
import com.demo.productservice.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
@Getter
public class CurrencyServiceImpl implements CurrencyService {

    private static int SCALE = 2;

    private final FixerClient fixerClient;

    @Override
    public BigDecimal convert(LocalDate date, String currencyFrom, String currencyTo, BigDecimal value) {
        CurrencyRate item = fixerClient.getCurrencyRates(currencyTo);

        Optional<BigDecimal> rate = Optional.ofNullable(item).map(CurrencyRate::getCurrencyRate)
                .map(m -> m.get(currencyFrom));
        if (!rate.isPresent()) {
            throw new ServiceException("Currency not found");
        }
        return value.divide(rate.get(), SCALE, RoundingMode.HALF_UP);
    }

    @Override
    public String defaultCurrency() {
        return "EUR";
    }
}
