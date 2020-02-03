package com.demo.productservice.product.controller.assembler;

import com.demo.productservice.category.dto.CategoryModel;
import com.demo.productservice.product.controller.ProductController;
import com.demo.productservice.product.converter.ProductConverter;
import com.demo.productservice.product.dto.ProductModel;
import com.demo.productservice.product.model.Product;
//import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
//import org.springframework.hateoas.RelProvider;
//import org.springframework.hateoas.ResourceAssembler;
//import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//@AllArgsConstructor
//@Component
//public class ProductAssembler implements ResourceAssembler<Product, ProductResource> {
//    private final ProductConverter productConverter;
//
//    private final EntityLinks entityLinks;
//
//    @Override
//    public ProductResource toResource(Product product) {
//        //Link link = linkTo(methodOn(ProductController.class).findById(product.getId())).withSelfRel();
//        ProductResource productResource = productConverter.convertToDto(product);
//        Link link = entityLinks.linkForSingleResource(productResource).withSelfRel();
//        productResource.add(link);
//        return productResource;
//    }
//}
@Component
public class ProductAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel> {

    private final ProductConverter productConverter;

    private final EntityLinks entityLinks;
    private final LinkRelationProvider relProvider;

    public ProductAssembler(final ProductConverter productConverter, EntityLinks entityLinks, LinkRelationProvider  relProvider) {
        super(ProductController.class, ProductModel.class);
        this.productConverter = productConverter;
        this.entityLinks = entityLinks;
        this.relProvider = relProvider;
    }

    @Override
    public ProductModel toModel(Product product) {
        ProductModel result = productConverter.convertToDto(product);
        Link selfLink = entityLinks.linkFor(ProductModel.class).withSelfRel();//linkForSingleResource(result).withSelfRel();
        Link categoryLink = entityLinks.linkToItemResource(CategoryModel.class, result.getCategoryId())
                .withRel(relProvider.getItemResourceRelFor(CategoryModel.class));
        result.add(selfLink);
        result.add(categoryLink);
        return result;
    }
}
