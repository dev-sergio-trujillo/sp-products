package com.spsolutions.service;

import com.spsolutions.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> findAll();

    public Optional<Product> findById(Long id);

    public Product save(Product product);

    public Product update(Long id, Product product);

    public void delete(Long id);
}
