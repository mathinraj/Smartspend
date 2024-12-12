package com.cts.smartspend.service;

import com.cts.smartspend.dto.BudgetDTO;
import com.cts.smartspend.entity.Budget;
import com.cts.smartspend.entity.User;
import com.cts.smartspend.repo.BudgetRepo;
import com.cts.smartspend.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    private final BudgetRepo budgetRepo;
    private final UserRepo userRepo;

    public BudgetService(BudgetRepo budgetRepo, UserRepo userRepo) {
        this.budgetRepo = budgetRepo;
        this.userRepo = userRepo;
    }

    public void setBudget(BudgetDTO budgetDTO) {
        User user = userRepo.findById(budgetDTO.getUserID())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Budget budget = new Budget();
        budget.setUser(user);
        budget.setCategory(budgetDTO.getCategory());
        budget.setLimit(budgetDTO.getLimit());
        budgetRepo.save(budget);
    }

    public List<BudgetDTO> getAllBudgets() {
        return budgetRepo.findAll().stream().map(budget -> new
                BudgetDTO(
                        budget.getCategory(),
                        budget.getLimit(),
                        budget.getUser().getId()
        )).collect(Collectors.toList());
    }

    public void updateBudget(Long id, BudgetDTO budgetDTO) {
        Budget budget = budgetRepo.findById(id)
                orElseThrow(() -> new RuntimeException("Budget not found"));

        budget.setCategory(budgetDTO.getCategory());
        budget.setLimit(budgetDTO.getLimit());
        budgetRepo.save(budget);
    }

    public void deleteBudget(Long id) {
        budgetRepo.deleteById(id);
    }
}
