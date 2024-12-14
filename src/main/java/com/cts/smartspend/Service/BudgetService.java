package com.cts.smartspend.service;

import com.cts.smartspend.dto.BudgetDTO;
import com.cts.smartspend.entity.Budget;
import com.cts.smartspend.entity.Category;
import com.cts.smartspend.exception.BudgetNotFoundException;
import com.cts.smartspend.exception.CategoryNotFoundException;
import com.cts.smartspend.repo.BudgetRepo;
import com.cts.smartspend.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepo budgetRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Budget> getAllBudgets() {
        return budgetRepo.findAll();
    }

    public Budget setBudget(BudgetDTO budgetDTO) {
        Category category = categoryRepo.findById(budgetDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Budget budget = new Budget();
        budget.setCategory(category);
        budget.setAmount(budgetDTO.getAmount());
        budget.setStartDate(budgetDTO.getStartDate());
        budget.setEndDate(budgetDTO.getEndDate());

        return budgetRepo.save(budget);
    }

    public Budget getBudgetById(Long id) {
        return budgetRepo.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Budget is not found"));
    }


    public Budget updateBudget(Long id, BudgetDTO budgetDTO) {
        Budget budget = getBudgetById(id);
        Category category = categoryRepo.findById(budgetDTO.getCategoryId())
                        .orElseThrow(() -> new CategoryNotFoundException("Category cannot be found"));

        budget.setCategory(category);
        budget.setAmount(budgetDTO.getAmount());
        budget.setStartDate(budgetDTO.getStartDate());
        budget.setEndDate(budgetDTO.getEndDate());

        return budgetRepo.save(budget);
    }

    public void deleteBudget(Long id) {
        Budget budget = budgetRepo.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Budget cannot be found"));
        budgetRepo.delete(budget);
    }
}
