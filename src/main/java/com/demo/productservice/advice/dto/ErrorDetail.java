package com.demo.productservice.advice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDetail {
    private String detail;
    private String type;
}
