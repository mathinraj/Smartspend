package com.cts.smartspend.service;

import com.cts.smartspend.dto.CategoryDTO;
import com.cts.smartspend.entity.Category;
import com.cts.smartspend.exception.CategoryNotFoundException;
import com.cts.smartspend.repo.CategoryRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category() ;
        category.setName(categoryDTO.getName());
        return categoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
    }

    public Category updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = getCategoryById(id);
        category.setName(categoryDTO.getName());
        return categoryRepo.save(category);
    }

    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepo.delete(category);
    }
}
