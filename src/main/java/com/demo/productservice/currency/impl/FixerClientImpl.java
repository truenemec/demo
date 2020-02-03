package com.demo.productservice.currency.impl;

import com.demo.productservice.currency.FixerClient;
import com.demo.productservice.currency.config.FixerConfiguration;
import com.demo.productservice.currency.model.CurrencyRate;
import com.demo.productservice.currency.dto.FixerDto;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@EnableConfigurationProperties(FixerConfiguration.class)
@Service
public class FixerClientImpl implements FixerClient {
    private final WebClient client;
    private final String apiKey;

    public FixerClientImpl(FixerConfiguration fixerConfiguration){
        client = WebClient.create(fixerConfiguration.getProtocol() + fixerConfiguration.getHost());
        apiKey = fixerConfiguration.getApiKey();
    }


    @Override
    public CurrencyRate getCurrencyRates(String base) {
        Mono<ClientResponse> result = client.get()
                .uri("/api/latest?access_key=" + apiKey + "&base=" + base)
                .accept(MediaType.APPLICATION_JSON).exchange();

        FixerDto fixerDto = result.flatMap(res -> res.bodyToMono(FixerDto.class)).block();
        return CurrencyRate.builder().currencyRate(fixerDto.getRates()).build();
    }
}

