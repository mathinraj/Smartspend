package com.cts.smartspend.controller;

import com.cts.smartspend.dto.CategoryDTO;
import com.cts.smartspend.entity.Category;
import com.cts.smartspend.service.CategoryService;
import jakarta.validation.Valid;
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

    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO category = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
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

