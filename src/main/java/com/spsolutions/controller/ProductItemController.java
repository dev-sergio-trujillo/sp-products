package com.spsolutions.controller;

import com.spsolutions.entity.CategoryEntity;
import com.spsolutions.entity.ProductEntity;
import com.spsolutions.model.ProductModel;
import com.spsolutions.service.ProductService;
import com.spsolutions.translate.ProductTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RefreshScope
@RestController
public class ProductItemController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTranslator productTranslator;

    @GetMapping("/product/findAll")
    public ResponseEntity<CollectionModel<ProductModel>> findAll(){
        List<ProductEntity> productEntities = productService.findAll();
        return new ResponseEntity<>(
            productTranslator.toCollectionModel(productEntities),
            HttpStatus.OK
        );
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductModel> findById(@PathVariable("id") Long id){
        ProductEntity productEntity = productService.findById(id).orElse(new ProductEntity());

        return new ResponseEntity<>(
            productTranslator.toModel(productEntity),
            HttpStatus.OK
        );
    }

    @PostMapping("/product")
    public ResponseEntity<ProductModel> save(@RequestBody ProductModel productModel){
        ProductEntity productEntity = productService.save(ProductEntity.builder()
                                                                       .sku(productModel.getSku())
                                                                       .price(productModel.getPrice())
                                                                       .name(productModel.getName())
                                                                       .createdAt(productModel.getCreatedAt())
                                                                       .category(CategoryEntity.builder()
                                                                                               .idCategory(productModel.getCategory().getIdCategory())
                                                                                               .build())
                                                                       .build());

        return new ResponseEntity<>(
            productTranslator.toModel(productEntity),
            HttpStatus.OK
        );
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<ProductModel> update(@PathVariable("id") Long id, @RequestBody ProductModel productModel){
        Optional<ProductEntity> productEntity = productService.findById(id);

        if(!productEntity.isPresent())
            return ResponseEntity.notFound().build();


        ProductEntity pu = productService.update(id, ProductEntity.builder()
                                               .idProduct(productModel.getIdProduct())
                                               .sku(productModel.getSku())
                                               .name(productModel.getName())
                                               .createdAt(productModel.getCreatedAt())
                                               .category(
                                                       CategoryEntity.builder()
                                                                    .idCategory(productModel.getCategory().getIdCategory())
                                                                    .build()
                                               )
                                               .build());

        return new ResponseEntity<>(
            productTranslator.toModel(pu),
            HttpStatus.OK
        );
    }

    @DeleteMapping("/product/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        productService.delete(id);
    }
}
