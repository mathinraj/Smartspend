package com.cts.Smartspend.Service;

import com.cts.Smartspend.Entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    public Category createCategory(Category category) {
        return category;
    }

    public List<Category> getAllCategories() {
        return ;
    }

}
