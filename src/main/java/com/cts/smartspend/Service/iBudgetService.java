package com.cts.smartspend.service;

import com.cts.smartspend.dto.BudgetDTO;

import java.util.List;

public interface IBudgetService {
    List<BudgetDTO> getAllBudgets();

    BudgetDTO setBudget(BudgetDTO budgetDTO);

    BudgetDTO getBudgetById(Long id);

    List<BudgetDTO> getBudgetByCategoryId(Long id);

    BudgetDTO updateBudget(Long id, BudgetDTO budgetDTO);

    void deleteBudget(Long id);
}
