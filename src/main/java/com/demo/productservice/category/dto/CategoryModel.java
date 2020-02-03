package com.demo.productservice.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
//import org.springframework.hateoas.ResourceSupport;
//import org.springframework.hateoas.core.Relation;
import org.springframework.hateoas.server.core.Relation;


import javax.validation.constraints.NotNull;

@Relation(value = "category", collectionRelation = "categories")
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
//@DependencyConstraint(attribute = "parentId", dependsOn = {"categoryId"}, message = "{MDLERR0002}")
//@DependencyConstraint(attribute = "parentId", dependsOn = {"name"}, message = "{MDLERR0002}")
public class CategoryModel extends RepresentationModel<CategoryModel> {
    private Long categoryId;
    @NotNull(message = "{MDLERR0001}")
    private String name;
    private Long parentId;
}
