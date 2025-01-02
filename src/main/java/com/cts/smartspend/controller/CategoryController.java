package com.cts.smartspend.controller;

import com.cts.smartspend.dto.CategoryDTO;
import com.cts.smartspend.exception.CategoryNotFoundException;
import com.cts.smartspend.serviceImpl.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO category = categoryService.createCategory(categoryDTO);
        logger.info("Category created successfully");
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> category = categoryService.getAllCategories();
        logger.info("Categories found");
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
            CategoryDTO categoryDTO = categoryService.getCategoryById(id);
            logger.info("Category with ID-{} is found", id);
            return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
            CategoryDTO category = categoryService.updateCategory(id, categoryDTO);
            logger.info("Category updated successfully");
            return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        logger.info("Category with ID-{} is deleted", id);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}
