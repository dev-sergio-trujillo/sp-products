package com.spsolutions.service;

import com.spsolutions.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<CategoryEntity> findAll();

    public Optional<CategoryEntity> findById(Long id);

    public CategoryEntity save(CategoryEntity productEntity);

    public CategoryEntity update(Long id, CategoryEntity productEntity);

    public void delete(Long id);
}
