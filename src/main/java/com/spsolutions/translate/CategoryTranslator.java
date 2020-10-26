package com.spsolutions.translate;

import com.spsolutions.controller.CategoryItemController;
import com.spsolutions.controller.ProductItemController;
import com.spsolutions.entity.CategoryEntity;
import com.spsolutions.entity.ProductEntity;
import com.spsolutions.model.CategoryModel;
import com.spsolutions.model.ProductModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoryTranslator extends RepresentationModelAssemblerSupport<CategoryEntity, CategoryModel> {

    public CategoryTranslator() {
        super(CategoryItemController.class, CategoryModel.class);
    }

    @Override
    public CategoryModel toModel(CategoryEntity entity) {

        CategoryModel categoryModel = new CategoryModel();

        categoryModel.add(
                linkTo(
                        methodOn(CategoryItemController.class).findById(entity.getIdCategory())
                ).withSelfRel()
        );

        categoryModel.setIdCategory(entity.getIdCategory());
        categoryModel.setCategory(entity.getCategory());
        categoryModel.setCreatedAt(entity.getCreatedAt());
        categoryModel.setProducts(toProductoModel(entity.getProducts()));

        return categoryModel;
    }

    @Override
    public CollectionModel<CategoryModel> toCollectionModel(Iterable<? extends CategoryEntity> entities) {

        CollectionModel<CategoryModel> categoryModels = super.toCollectionModel(entities);

        categoryModels.add(
                linkTo(
                        methodOn(CategoryItemController.class).findAll()
                ).withSelfRel()
        );

        return categoryModels;
    }

    private List<ProductModel> toProductoModel(List<ProductEntity> products) {

        if(products.isEmpty())
            return Collections.emptyList();

        return products.stream()
                        .map(productEntity -> ProductModel.builder()
                                                          .idProduct(productEntity.getIdProduct())
                                                          .sku(productEntity.getSku())
                                                          .price(productEntity.getPrice())
                                                          .name(productEntity.getName())
                                                          .createdAt(productEntity.getCreatedAt())
                                                          .build()
                                                          .add(
                                                                  linkTo(
                                                                          methodOn(ProductItemController.class).findById(productEntity.getIdProduct())
                                                                  ).withSelfRel()
                                                          )
                        ).collect(Collectors.toList());
    }
}
