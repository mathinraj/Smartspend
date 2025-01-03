package com.cts.smartspend.serviceImpl;

import com.cts.smartspend.dto.CategoryDTO;
import com.cts.smartspend.entity.Category;
import com.cts.smartspend.exception.CategoryNotFoundException;
import com.cts.smartspend.repo.CategoryRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    private final Logger logger = LoggerFactory.getLogger(CategoryServiceTest.class);

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void testCreateCategory() {
        logger.info("Testing CreateCategory method");
        CategoryDTO categoryDTO = new CategoryDTO(1L,"Food");
        Category category = new Category();
        category.setName("Foods");

        when(categoryRepo.save(any())).thenReturn(category);

        CategoryDTO result = categoryService.createCategory(categoryDTO);
        assertEquals(categoryDTO.getName(),result.getName());
        logger.info("Category creation method passed");
    }
    @Test
    void testGetAllCategories() {
        logger.info("Testing GetAllCategories method");
        Category category1 = new Category(1L, "Groceries", null);
        Category category2 = new Category(2L, "Entertainment", null);
        when(categoryRepo.findAll()).thenReturn(List.of(category1, category2));

        List<CategoryDTO> result = categoryService.getAllCategories();

        assertEquals(2, result.size());
        assertEquals("Entertainment", result.get(1).getName());
        logger.info("GetAllCategories method passed");
    }

    @Test
    void testGetAllCategories_ThrowsCategoryNotFoundException() {
        logger.info("Testing CategoryNotFoundException Exception");
        when(categoryRepo.findAll()).thenReturn(Collections.emptyList());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.getAllCategories());
        logger.info("CategoryNotFoundException Exception thrown");
    }

    @Test
    void testGetCategoryById() {
        logger.info("Testing GetCategoryById method");
        Category category = new Category(1L, "Groceries", null);
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));

        CategoryDTO result = categoryService.getCategoryById(1L);

        assertEquals("Groceries", result.getName());
        logger.info("GetCategoryById method passed");
    }

    @Test
    void testDeleteCategory() {
        logger.info("Testing DeleteCategory method");
        Category category = new Category(1L, "Groceries", null);
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));

        categoryService.deleteCategory(1L);

        verify(categoryRepo, times(1)).delete(category);
        logger.info("DeleteCategory method passed");
    }
}