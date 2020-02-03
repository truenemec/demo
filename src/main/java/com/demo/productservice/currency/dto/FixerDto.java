package com.demo.productservice.currency.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class FixerDto {
    private String base;
    private Map<String, BigDecimal> rates;
}
