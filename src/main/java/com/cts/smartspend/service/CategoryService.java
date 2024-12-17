package com.cts.smartspend.service;

import com.cts.smartspend.dto.CategoryDTO;
import com.cts.smartspend.entity.Category;
import com.cts.smartspend.exception.CategoryNotFoundException;
import com.cts.smartspend.repo.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
//        Category category = Category.builder()
//                .name(categoryDTO.getName())
//                .build();
        Category category = new Category();
        category.setName(categoryDTO.getName());
        Category savedCategory = categoryRepo.save(category);
        return convertToCategoryDTO(savedCategory);
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> category = categoryRepo.findAll();
        return convertToCategoryDTO(category);
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        return convertToCategoryDTO(category);
    }

    @Transactional
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        existingCategory.setName(categoryDTO.getName());
        Category savedCategory = categoryRepo.save(existingCategory);
        return convertToCategoryDTO(savedCategory);
    }

    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        categoryRepo.delete(category);
    }

    public CategoryDTO convertToCategoryDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }

    public List<CategoryDTO> convertToCategoryDTO(List<Category> categories) {
        return categories.stream()
                .map(this::convertToCategoryDTO)
                .collect(Collectors.toList());
    }
}
