package com.demo.productservice.category.dto.mapper;

import com.demo.productservice.category.dto.CategoryModel;
import com.demo.productservice.category.model.Category;
import org.mapstruct.*;

@Mapper//(uses = CategoryMapper.Mapper1.class)
public interface CategoryMapper {
    @Mappings({
            @Mapping(target = "categoryId", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "parentId", source = "parent.id")
    })
    CategoryModel toDto(Category category);
//    @InheritConfiguration
//    void updateDto(Category category, @MappingTarget CategoryResource categoryResource);

    @Mappings({
            @Mapping(target = "id", source = "categoryId"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "parent.id", source = "parentId", nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
    })
    //@InheritInverseConfiguration
    Category toModel(CategoryModel category);

//    default Category categoryResourceToCategory(CategoryResource categoryResource){
//        if(categoryResource.getCategoryId() == null){
//            return null;
//        }
//        return Category.builder().id(categoryResource.getCategoryId()).build();
//    }
//    @InheritConfiguration
//    void updateModel(CategoryResource categoryResource, @MappingTarget Category category);
//    @Mapper
//    interface Mapper1 {
//        @Mapping(source = "parentId", target = "id")
//        Category get(String parentId);
//}

}
