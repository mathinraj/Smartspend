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

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();
        Category savedCategory = categoryRepo.save(category);
        return convertToCategoryDTO(savedCategory);
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

    public CategoryDTO convertToCategoryDTO(Category category) {
        return new CategoryDTO(
                category.getName()
        );

    }
}
