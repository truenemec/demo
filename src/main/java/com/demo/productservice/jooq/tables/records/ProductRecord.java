/*
 * This file is generated by jOOQ.
 */
package com.demo.productservice.jooq.tables.records;


import com.demo.productservice.jooq.tables.Product;

import java.math.BigDecimal;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProductRecord extends UpdatableRecordImpl<ProductRecord> implements Record4<Long, String, Long, BigDecimal> {

    private static final long serialVersionUID = 790282102;

    /**
     * Setter for <code>public.product.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.product.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.product.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.product.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.product.category_id</code>.
     */
    public void setCategoryId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.product.category_id</code>.
     */
    public Long getCategoryId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.product.price</code>.
     */
    public void setPrice(BigDecimal value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.product.price</code>.
     */
    public BigDecimal getPrice() {
        return (BigDecimal) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, Long, BigDecimal> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, String, Long, BigDecimal> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Product.PRODUCT.ID;
    }

    @Override
    public Field<String> field2() {
        return Product.PRODUCT.NAME;
    }

    @Override
    public Field<Long> field3() {
        return Product.PRODUCT.CATEGORY_ID;
    }

    @Override
    public Field<BigDecimal> field4() {
        return Product.PRODUCT.PRICE;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Long component3() {
        return getCategoryId();
    }

    @Override
    public BigDecimal component4() {
        return getPrice();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public Long value3() {
        return getCategoryId();
    }

    @Override
    public BigDecimal value4() {
        return getPrice();
    }

    @Override
    public ProductRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public ProductRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public ProductRecord value3(Long value) {
        setCategoryId(value);
        return this;
    }

    @Override
    public ProductRecord value4(BigDecimal value) {
        setPrice(value);
        return this;
    }

    @Override
    public ProductRecord values(Long value1, String value2, Long value3, BigDecimal value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProductRecord
     */
    public ProductRecord() {
        super(Product.PRODUCT);
    }

    /**
     * Create a detached, initialised ProductRecord
     */
    public ProductRecord(Long id, String name, Long categoryId, BigDecimal price) {
        super(Product.PRODUCT);

        set(0, id);
        set(1, name);
        set(2, categoryId);
        set(3, price);
    }
}
