package com.demo.productservice.product.converter;

import com.demo.productservice.category.model.Category;
import com.demo.productservice.category.service.CategoryService;
import com.demo.productservice.currency.CurrencyService;
import com.demo.productservice.exception.ServiceException;
import com.demo.productservice.product.dto.ProductModel;
import com.demo.productservice.product.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductConverter {

    private final CurrencyService currencyService;

    private final CategoryService categoryService;

    public ProductModel convertToDto(Product product){
        return ProductModel.builder()
                .productId(product.getId())
                .name(product.getName())
                .currency(currencyService.defaultCurrency())
                .price(product.getPrice())
                .categoryId(Optional.ofNullable(product.getCategory())
                        .map(Category::getId)
                        .orElse(null))
                .build();
    }

    public Product convertFromDto(ProductModel productDto){
        BigDecimal price = productDto.getPrice();
        if(!Objects.equals(productDto.getCurrency(), currencyService.defaultCurrency())){
            price = currencyService.convert(LocalDate.now(), productDto.getCurrency(),
                    currencyService.defaultCurrency(), productDto.getPrice());
        }
        Optional<Category> category = categoryService.findById(productDto.getCategoryId());
        if(!category.isPresent()){
            throw new ServiceException("Category with id " + productDto.getProductId() + " not found");
        }
        return Product.builder()
                .id(productDto.getProductId())
                .name(productDto.getName())
                .price(price)
                .category(category.get())
                .build();
    }
}
