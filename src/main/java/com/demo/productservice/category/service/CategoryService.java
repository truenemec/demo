package com.demo.productservice.category.service;

import com.demo.productservice.category.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {

    Page<Category> findAll(Pageable pageable);

    Optional<Category> findById(Long id);

    Category save(Category category);

    void deleteById(Long id);
}
