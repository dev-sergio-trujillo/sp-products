package com.spsolutions.service.impl;

import com.spsolutions.entity.Product;
import com.spsolutions.repository.ProductRepository;
import com.spsolutions.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {

        Optional<Product> pdb = productRepository.findById(id);

        if(pdb.isEmpty())
            return new Product();

        product.setIdProduct(id);
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {

        Optional<Product> pdb = productRepository.findById(id);
        pdb.ifPresent(product -> productRepository.delete(product));
    }
}
