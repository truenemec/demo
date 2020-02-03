package com.demo.productservice.product.repository;

import com.demo.productservice.product.model.Product;

import java.util.Optional;

public interface ProductRepositoryJooq {

    Optional<Product> findById(Long id);

}
