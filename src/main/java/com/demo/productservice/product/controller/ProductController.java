package com.demo.productservice.product.controller;

import com.demo.productservice.exception.ServiceException;
import com.demo.productservice.product.controller.assembler.ProductAssembler;
import com.demo.productservice.product.converter.ProductConverter;
import com.demo.productservice.product.dto.ProductModel;
import com.demo.productservice.product.model.Product;
import com.demo.productservice.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;

import org.springframework.hateoas.PagedModel;

import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/product")
@ExposesResourceFor(ProductModel.class)
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;
    private final PagedResourcesAssembler<Product> productPagedResourcesAssembler;
    private final ProductAssembler productAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedModel<ProductModel>> findAll(@NotNull final Pageable pageable) {
        return ResponseEntity.ok(productPagedResourcesAssembler.toModel(productService.findAll(pageable), productAssembler));

    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductModel> findById(@PathVariable("id") Long id) {
        return ResponseEntity.of(productService.findById(id).map(productAssembler::toModel)) ;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductModel> save(@RequestBody ProductModel productDto) {
        Optional<ProductModel> result = Optional.of(productDto)
                .map(productConverter::convertFromDto)
                .map(productService::save)
                .map(productAssembler::toModel);
        return new ResponseEntity<>(result.orElseThrow(() -> new ServiceException("saved object must not be null")),
                HttpStatus.CREATED);

    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductModel> update(@PathVariable("id") Long id, @RequestBody ProductModel productDto) {
        productDto = productDto.toBuilder().productId(id).build();
        Optional<ProductModel> result = Optional.of(productDto)
                .map(productConverter::convertFromDto)
                .map(productService::save)
                .map(productAssembler::toModel);
        return ResponseEntity.of(result);

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
