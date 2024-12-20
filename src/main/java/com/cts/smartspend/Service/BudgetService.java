package com.cts.smartspend.service;

import com.cts.smartspend.dto.BudgetDTO;
import com.cts.smartspend.entity.Budget;
import com.cts.smartspend.entity.Category;
import com.cts.smartspend.entity.Expense;
import com.cts.smartspend.exception.BudgetNotFoundException;
import com.cts.smartspend.exception.CategoryNotFoundException;
import com.cts.smartspend.exception.ExpenseNotFoundException;
import com.cts.smartspend.repo.BudgetRepo;
import com.cts.smartspend.repo.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepo budgetRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public List<BudgetDTO> getAllBudgets() {
        List<Budget> budget = budgetRepo.findAll();
        return convertToBudgetDTO(budget);
    }

    @Transactional
    public BudgetDTO setBudget(BudgetDTO budgetDTO) {
        Category category = categoryRepo.findById(budgetDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

//        Budget budget = Budget.builder()
//                .category(category)
//                .amount(budgetDTO.getAmount())
//                .startDate(budgetDTO.getStartDate())
//                .endDate(budgetDTO.getEndDate())
//                .build();

        Budget budget = new Budget();
        budget.setCategory(category);
        budget.setAmount(budgetDTO.getAmount());
        budget.setStartDate(budgetDTO.getStartDate());
        budget.setEndDate(budgetDTO.getEndDate());
        Budget savedBudget = budgetRepo.save(budget);
        return convertToBudgetDTO(savedBudget);
    }

    public BudgetDTO getBudgetById(Long id) {
        Budget budget = budgetRepo.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Budget is not found"));
        return convertToBudgetDTO(budget);
    }

    public List<BudgetDTO> getBudgetByCategoryId(Long id) {
        List<Budget> budget = budgetRepo.findAllByCategoryId(id);
        return convertToBudgetDTO(budget);
    }

    @Transactional
    public BudgetDTO updateBudget(Long id, BudgetDTO budgetDTO) {
        Budget budget = getBudgetEntityById(id);
        Category category = categoryRepo.findById(budgetDTO.getCategoryId())
                        .orElseThrow(() -> new CategoryNotFoundException("Category cannot be found"));

        budget.setCategory(category);
        budget.setAmount(budgetDTO.getAmount());
        budget.setStartDate(budgetDTO.getStartDate());
        budget.setEndDate(budgetDTO.getEndDate());
        Budget savedBudget = budgetRepo.save(budget);
        return convertToBudgetDTO(savedBudget);
    }

    @Transactional
    public void deleteBudget(Long id) {
        Budget budget = budgetRepo.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Budget cannot be found"));
        budgetRepo.delete(budget);
    }

    private BudgetDTO convertToBudgetDTO(Budget budget) {
        return new BudgetDTO(
                budget.getAmount(),
                budget.getCategory().getId(),
                budget.getStartDate(),
                budget.getEndDate()
        );
    }

    private List<BudgetDTO> convertToBudgetDTO(List<Budget> budget) {
        return budget.stream()
                .map(this::convertToBudgetDTO)
                .collect(Collectors.toList());
    }

    private Budget getBudgetEntityById(Long id) {
        return budgetRepo.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Budget with ID " + id + " not found"));
    }

}
