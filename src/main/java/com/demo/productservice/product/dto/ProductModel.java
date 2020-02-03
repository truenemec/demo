package com.demo.productservice.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
@Relation(value = "product", collectionRelation = "products")
public class ProductModel extends RepresentationModel<ProductModel> {
    private Long productId;
    private String name;
    private BigDecimal price;
    private Long categoryId;
    private String currency;
}
