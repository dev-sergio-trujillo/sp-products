package com.spsolutions.translate;

import com.spsolutions.controller.ProductItemController;
import com.spsolutions.entity.ProductEntity;
import com.spsolutions.model.CategoryModel;
import com.spsolutions.model.ProductModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@Slf4j
public class ProductTranslator extends RepresentationModelAssemblerSupport<ProductEntity, ProductModel> {

    public ProductTranslator() {
        super(ProductItemController.class, ProductModel.class);
    }

    @Override
    public ProductModel toModel(ProductEntity entity) {

        ProductModel productModel = instantiateModel(entity);

        productModel.add(
                linkTo(
                        methodOn(ProductItemController.class).findById(entity.getIdProduct())
                ).withSelfRel()
        );

        productModel.setIdProduct(entity.getIdProduct());
        productModel.setSku(entity.getSku());
        productModel.setPrice(entity.getPrice());
        productModel.setName(entity.getName());
        productModel.setCreatedAt(entity.getCreatedAt());
        productModel.setCategory(toModelFromEntity(entity));
//        log.trace(entity.getCategory().toString());
        return productModel;
    }

    private CategoryModel toModelFromEntity(ProductEntity entity) {
        return  CategoryModel.builder()
                             .idCategory(entity.getCategory().getIdCategory())
                             .category(entity.getCategory().getCategory())
                             .createdAt(entity.getCategory().getCreatedAt())
                             .build();
    }

}
