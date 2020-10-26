package com.spsolutions.service.impl;

import com.spsolutions.entity.CategoryEntity;
import com.spsolutions.repository.CategoryRepository;
import com.spsolutions.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryEntity> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public CategoryEntity save(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity update(Long id, CategoryEntity categoryEntity) {

        Optional<CategoryEntity> pdb = categoryRepository.findById(id);

        if(pdb.isEmpty())
            return new CategoryEntity();

        categoryEntity.setIdCategory(id);
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public void delete(Long id) {

        Optional<CategoryEntity> pdb = categoryRepository.findById(id);
        pdb.ifPresent(categoryEntity -> categoryRepository.delete(categoryEntity));
    }
}
