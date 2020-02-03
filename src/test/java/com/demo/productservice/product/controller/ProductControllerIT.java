package com.demo.productservice.product.controller;

import com.demo.productservice.AbstractIT;
import com.demo.productservice.product.dto.ProductModel;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductControllerIT extends AbstractIT {

    private static final String BASE_API_URL = "/api/v1/product/";

    @Override
    public String getApiPath() {
        return BASE_API_URL;
    }

    @Test
    public void shouldProperlyHandleGetById(){
        ProductModel productDtoExpected = ProductModel.builder()
                .productId(1L)
                .price(new BigDecimal("10.01"))
                .name("product_1")
                .currency("EUR")
                .categoryId(1L)
                .build();
        ProductModel result = getRestTemplate().getForObject(getUrl() + productDtoExpected.getProductId(), ProductModel.class);
        assertThat(result).isEqualToComparingFieldByField(productDtoExpected);
    }

    @Test
    public void shouldProperlyHandleSave(){
        ProductModel productDto = ProductModel.builder()
                .price(BigDecimal.valueOf(Math.random()))
                .name("test")
                .currency("EUR")
                .categoryId(1L)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<ProductModel> request = new HttpEntity<>(productDto, headers);

        ResponseEntity<ProductModel> saved = getRestTemplate().postForEntity(getUrl(), request, ProductModel.class);
        assertThat(saved.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(saved.getBody()).isEqualToIgnoringGivenFields(productDto,"productId");
    }

    @Test
    public void shouldNotProperlyHandleSaveWithIncorrectCategory(){
        ProductModel productDto = ProductModel.builder()
                .price(BigDecimal.valueOf(Math.random()))
                .name("test")
                .currency("EUR")
                .categoryId(99L)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<ProductModel> request = new HttpEntity<>(productDto, headers);

        ResponseEntity<ProductModel> result = getRestTemplate().postForEntity(getUrl(), request, ProductModel.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void shouldProperlyHandleUpdate(){
        ProductModel productDto = ProductModel.builder()
                .price(BigDecimal.valueOf(Math.random()))
                .name("test")
                .currency("EUR")
                .categoryId(1L)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<ProductModel> request = new HttpEntity<>(productDto, headers);

        ProductModel saved = getRestTemplate().postForObject(getUrl(), request, ProductModel.class);
        ProductModel productDtoUpdated = saved.toBuilder().name("new_name").build();
        ResponseEntity<ProductModel> res = getRestTemplate().exchange(getUrl() + saved.getProductId(),
                HttpMethod.PUT, new HttpEntity<>(productDtoUpdated, headers) , ProductModel.class);
        assertThat(res.getBody()).isEqualToComparingFieldByField(productDtoUpdated);
    }

    @Test
    public void shouldProperlyHandleSaveWithDifferentCurrency(){
        ProductModel productDto = ProductModel.builder()
                .price(BigDecimal.valueOf(Math.random()))
                .name("test")
                .currency("RUB")
                .categoryId(1L)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<ProductModel> request = new HttpEntity<>(productDto, headers);

        ProductModel saved = getRestTemplate().postForObject(getUrl(), request, ProductModel.class);
        assertThat(saved).isEqualToIgnoringGivenFields(productDto,"productId", "price", "currency");
        assertThat(saved.getCurrency()).isEqualTo("EUR");
    }

    @Test
    public void shouldProperlyHandleDelete(){
        ProductModel productDto = ProductModel.builder()
                .price(BigDecimal.valueOf(Math.random()))
                .name("test")
                .currency("EUR")
                .categoryId(1L)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<ProductModel> request = new HttpEntity<>(productDto, headers);

        ProductModel saved = getRestTemplate().postForObject(getUrl(), request, ProductModel.class);
        getRestTemplate().delete(getUrl() + saved.getProductId());
        ResponseEntity<ProductModel> result = getRestTemplate().getForEntity(getUrl() + saved.getProductId(), ProductModel.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
