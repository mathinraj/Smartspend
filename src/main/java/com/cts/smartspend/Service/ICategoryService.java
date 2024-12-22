package com.cts.smartspend.service;

import com.cts.smartspend.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long id);

    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);

    void deleteCategory(Long id);
}
