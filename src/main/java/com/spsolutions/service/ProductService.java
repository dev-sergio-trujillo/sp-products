package com.spsolutions.service;

import com.spsolutions.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<ProductEntity> findAll();

    public Optional<ProductEntity> findById(Long id);

    public ProductEntity save(ProductEntity productEntity);

    public ProductEntity update(Long id, ProductEntity productEntity);

    public void delete(Long id);
}
