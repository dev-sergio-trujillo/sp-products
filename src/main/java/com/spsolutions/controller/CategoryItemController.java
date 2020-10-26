package com.spsolutions.controller;

import com.spsolutions.entity.CategoryEntity;
import com.spsolutions.model.CategoryModel;
import com.spsolutions.service.CategoryService;
import com.spsolutions.translate.CategoryTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
public class CategoryItemController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryTranslator categoryTranslator;

    @GetMapping("/category/findAll")
    public ResponseEntity<CollectionModel<CategoryModel>> findAll(){
        List<CategoryEntity> categoryEntityList = categoryService.findAll();
        return new ResponseEntity<>(
            categoryTranslator.toCollectionModel(categoryEntityList),
            HttpStatus.OK
        );
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryModel> findById(@PathVariable("id") Long id){
        CategoryEntity categoryEntity = categoryService.findById(id).orElse(new CategoryEntity());

        return new ResponseEntity<>(
            categoryTranslator.toModel(categoryEntity),
            HttpStatus.OK
        );
    }
}
