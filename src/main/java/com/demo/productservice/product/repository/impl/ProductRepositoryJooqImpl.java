package com.demo.productservice.product.repository.impl;

import com.demo.productservice.jooq.tables.Category;
import com.demo.productservice.jooq.tables.records.CategoryRecord;
import com.demo.productservice.jooq.tables.records.ProductRecord;
import com.demo.productservice.product.model.Product;
import com.demo.productservice.product.repository.ProductRepositoryJooq;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@AllArgsConstructor
public class ProductRepositoryJooqImpl implements ProductRepositoryJooq {

    private final DSLContext dslContext;

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Record> record = dslContext.select().from(com.demo.productservice.jooq.tables.Product.PRODUCT)
                .join(Category.CATEGORY)
                .on(com.demo.productservice.jooq.tables.Product.PRODUCT.CATEGORY_ID.eq(Category.CATEGORY.ID))
                .where(com.demo.productservice.jooq.tables.Product.PRODUCT.ID.eq(id))
                .fetchOptional();
//        ;
//        Optional<Product> result = dslContext.select().from(Product.PRODUCT)
//                .where(Product.PRODUCT.ID.eq(id))
//                .fetchOptionalInto(ProductRecord.class)
//                .map(this::toEntity);
        //record.get().into()
        Optional<Product> result = record
                .map(v -> this.toEntity(v.into(com.demo.productservice.jooq.tables.Product.PRODUCT), v.into(Category.CATEGORY)));
        return result;
    }

    private Product toEntity(ProductRecord record) {
        return Product.builder()
                .price(record.getPrice())
                .id(record.getId())
                .name(record.getName())
                .build();
    }

    private com.demo.productservice.category.model.Category toEntity(CategoryRecord record) {
        return com.demo.productservice.category.model.Category.builder()
                .id(record.getId())
                .name(record.getName())
                .build();
    }

    private Product toEntity(ProductRecord productRecord, CategoryRecord categoryRecord) {
        return Product.builder()
                .price(productRecord.getPrice())
                .id(productRecord.getId())
                .name(productRecord.getName())
                .category(toEntity(categoryRecord))
                .build();
    }
}
