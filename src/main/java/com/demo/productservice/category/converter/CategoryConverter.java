package com.demo.productservice.category.converter;

import com.demo.productservice.category.dto.CategoryModel;
import com.demo.productservice.category.dto.mapper.CategoryMapper;
import com.demo.productservice.category.model.Category;
import com.demo.productservice.category.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryConverter {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryModel convertToDto(Category category) {
        return categoryMapper.toDto(category);
//        return CategoryResource.builder()
//                .categoryId(category.getId())
//                .name(category.getName())
//                .parentId(Optional.ofNullable(category)
//                        .map(Category::getParent)
//                        .map(Category::getId)
//                        .orElse(null))
//                .build();
    }

    public Category convertFromDto(CategoryModel categoryDto) {
        return categoryMapper.toModel(categoryDto);
//        Long parentId = categoryDto.getParentId();
//        Category parentCategory = null;
//        if (parentId != null) {
//            parentCategory = categoryService.findById(parentId)
//                    .orElseThrow(() -> new ServiceException("Illegal parent id " + parentId));
//        }
//        return Category.builder()
//                .id(categoryDto.getCategoryId())
//                .name(categoryDto.getName())
//                .parent(parentCategory)
//                .build();
    }
}
