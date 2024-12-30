package com.cts.smartspend.serviceImpl;

import com.cts.smartspend.dto.CategoryDTO;
import com.cts.smartspend.entity.Category;
import com.cts.smartspend.exception.CategoryNotFoundException;
import com.cts.smartspend.repo.CategoryRepo;
import com.cts.smartspend.service.ICategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        Category savedCategory = categoryRepo.save(category);
        return convertToCategoryDTO(savedCategory);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> category = categoryRepo.findAll();
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("Category not found");
        }
        return convertToCategoryDTO(category);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepo.findById(id).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException("Category with ID-" + id+ " is not found");
        }
        return convertToCategoryDTO(category);
    }

    @Override
    @Transactional
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with ID-" + id + " is not found"));

        existingCategory.setName(categoryDTO.getName());
        Category savedCategory = categoryRepo.save(existingCategory);
        return convertToCategoryDTO(savedCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with ID-" + id + " is not found"));

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
