package com.demo.productservice.category.controller;

import com.demo.productservice.category.controller.assembler.CategoryAssembler;
import com.demo.productservice.category.converter.CategoryConverter;
import com.demo.productservice.category.dto.CategoryModel;
import com.demo.productservice.category.model.Category;
import com.demo.productservice.category.service.CategoryService;
import com.demo.productservice.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.util.Optional;




@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/category")
@ExposesResourceFor(CategoryModel.class)
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryConverter categoryConverter;

    private final Validator validator;

    private final CategoryAssembler categoryAssembler;

    private final PagedResourcesAssembler<Category> pagedResourcesAssembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<PagedModel<CategoryModel>> findAll(final Pageable pageable) {
        Page<Category> result = categoryService.findAll(pageable);
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(result, categoryAssembler));
    }

    @GetMapping(value = "{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CategoryModel> findById(@PathVariable("id") Long id) {
        return ResponseEntity.of(categoryService.findById(id)
                .map(categoryAssembler::toModel));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryModel> save(@RequestBody @Validated CategoryModel categoryDto) {
        Optional<CategoryModel> result = Optional.of(categoryDto)
                .map(categoryConverter::convertFromDto)
                .map(categoryService::save)
                .map(categoryAssembler::toModel);
        return new ResponseEntity<>(result.orElseThrow(() -> new ServiceException("saved object must not be null")),
                HttpStatus.CREATED);

    }

    @PutMapping(value = "{id}", produces = MediaTypes.HAL_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryModel> update(@PathVariable("id") Long id, @RequestBody CategoryModel categoryDto) {
        categoryDto = categoryDto.toBuilder()
                .categoryId(id)
                .build();
        Optional<CategoryModel> result = Optional.of(categoryDto)
                .map(categoryConverter::convertFromDto)
                .map(categoryService::save)
                .map(categoryAssembler::toModel);
        return ResponseEntity.of(result);

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
