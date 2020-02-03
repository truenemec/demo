package com.demo.productservice.currency.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fixer")
@Getter
@Setter
public class FixerConfiguration {
    private String host;
    private String protocol;
    private String apiKey;
}
