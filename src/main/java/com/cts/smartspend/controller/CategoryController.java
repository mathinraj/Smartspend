package com.cts.smartspend.controller;

import com.cts.smartspend.dto.CategoryDTO;
import com.cts.smartspend.serviceImpl.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO category = categoryService.createCategory(categoryDTO);
        logger.info("Category created successfully");
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> category = categoryService.getAllCategories();
        if (category == null) {
            logger.info("No categories found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Categories found");
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long id) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        if (categoryDTO == null) {
            logger.info("Category with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Category found with ID {}", id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO category = categoryService.updateCategory(id, categoryDTO);
        if (category == null) {
            logger.warn("Category with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Category updated successfully");
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        logger.info("Category deleted successfully");
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}


//package com.example.smartspend.controller;
//
//import com.example.smartspend.dto.CategoryDTO;
//import com.example.smartspend.entity.Category;
//import com.example.smartspend.service.CategoryService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//        import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/categories")
//public class CategoryController {
//
//    private final CategoryService categoryService;
//
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    @PostMapping
//    public ResponseEntity<String> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
//        categoryService.addCategory(categoryDTO);
//        return ResponseEntity.ok("Category added successfully");
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Category>> getAllCategories() {
//        List<Category> categories = categoryService.getAllCategories();
//        return ResponseEntity.ok(categories);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
//        Category category = categoryService.getCategoryById(id);
//        return ResponseEntity.ok(category);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
//        categoryService.updateCategory(id, categoryDTO);
//        return ResponseEntity.ok("Category updated successfully");
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
//        categoryService.deleteCategory(id);
//        return ResponseEntity.ok("Category deleted successfully");
//    }
//}

