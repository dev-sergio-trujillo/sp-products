package com.spsolutions.service.impl;

import com.spsolutions.entity.ProductEntity;
import com.spsolutions.repository.ProductRepository;
import com.spsolutions.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity update(Long id, ProductEntity productEntity) {

        Optional<ProductEntity> pdb = productRepository.findById(id);

        if(pdb.isEmpty())
            return new ProductEntity();

        productEntity.setIdProduct(id);
        return productRepository.save(productEntity);
    }

    @Override
    public void delete(Long id) {

        Optional<ProductEntity> pdb = productRepository.findById(id);
        pdb.ifPresent(productEntity -> productRepository.delete(productEntity));
    }
}
