package com.demo.productservice.category.controller.assembler;

import com.demo.productservice.category.controller.CategoryController;
import com.demo.productservice.category.converter.CategoryConverter;
import com.demo.productservice.category.dto.CategoryModel;
import com.demo.productservice.category.model.Category;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class CategoryAssembler extends RepresentationModelAssemblerSupport<Category, CategoryModel> {

    public CategoryAssembler(final CategoryConverter productConverter) {
        super(CategoryController.class, CategoryModel.class);
        this.categoryConverter = productConverter;
    }


    private final CategoryConverter categoryConverter;

    private final Class<CategoryController> clazz = CategoryController.class;

    @Override
    public CategoryModel toModel(Category category) {
        Link link = linkTo(methodOn(clazz).findById(category.getId())).withSelfRel();
        CategoryModel result = categoryConverter.convertToDto(category);
        result.add(link);
        return result;
    }
}
