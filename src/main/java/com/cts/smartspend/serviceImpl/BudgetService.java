package com.cts.smartspend.serviceImpl;

import com.cts.smartspend.dto.BudgetDTO;
import com.cts.smartspend.entity.Budget;
import com.cts.smartspend.entity.Category;
import com.cts.smartspend.exception.OverlappingBudgetException;
import com.cts.smartspend.service.IBudgetService;
import com.cts.smartspend.exception.BudgetNotFoundException;
import com.cts.smartspend.exception.CategoryNotFoundException;
import com.cts.smartspend.repo.BudgetRepo;
import com.cts.smartspend.repo.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetService implements IBudgetService {

    @Autowired
    private BudgetRepo budgetRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<BudgetDTO> getAllBudgets() {
        List<Budget> budget = budgetRepo.findAll();
        return convertToBudgetDTO(budget);
    }

    @Override
    @Transactional
    public BudgetDTO setBudget(BudgetDTO budgetDTO) {

        List<Budget> overlappingBudgets = budgetRepo.findOverlappingBudgets(
                budgetDTO.getCategoryId(), budgetDTO.getStartDate(), budgetDTO.getEndDate());

        if (!overlappingBudgets.isEmpty()) {
            throw new OverlappingBudgetException("A budget already exists for the same category with overlapping dates.");
        }

        Category category = categoryRepo.findById(budgetDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        Budget budget = new Budget();
        budget.setCategory(category);
        budget.setAmount(budgetDTO.getAmount());
        budget.setStartDate(budgetDTO.getStartDate());
        budget.setEndDate(budgetDTO.getEndDate());
        Budget savedBudget = budgetRepo.save(budget);
        return convertToBudgetDTO(savedBudget);
    }

    @Override
    public BudgetDTO getBudgetById(Long id) {
        Budget budget = budgetRepo.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Budget is not found"));
        return convertToBudgetDTO(budget);
    }

    @Override
    public List<BudgetDTO> getBudgetByCategoryId(Long id) {
        if (!categoryRepo.existsById(id)) {
            throw new CategoryNotFoundException("Category is not found");
        }
        List<Budget> budget = budgetRepo.findAllByCategoryId(id);
        if (budget.isEmpty()) {
            throw new BudgetNotFoundException("Budget is not found");
        }
        return convertToBudgetDTO(budget);
    }

    @Override
    @Transactional
    public BudgetDTO updateBudget(Long id, BudgetDTO budgetDTO) {
        Budget budget = getBudgetEntityById(id);

        List<Budget> overlappingBudgets = budgetRepo.findOverlappingBudgets(
                budgetDTO.getCategoryId(), budgetDTO.getStartDate(), budgetDTO.getEndDate());

        if (!overlappingBudgets.isEmpty() && !overlappingBudgets.get(0).getId().equals(id)) {
            throw new OverlappingBudgetException("A budget already exists for the same category with overlapping dates.");
        }

        Category category = categoryRepo.findById(budgetDTO.getCategoryId())
                        .orElseThrow(() -> new CategoryNotFoundException("Category cannot be found"));

        budget.setCategory(category);
        budget.setAmount(budgetDTO.getAmount());
        budget.setStartDate(budgetDTO.getStartDate());
        budget.setEndDate(budgetDTO.getEndDate());
        Budget savedBudget = budgetRepo.save(budget);
        return convertToBudgetDTO(savedBudget);
    }

    @Override
    @Transactional
    public void deleteBudget(Long id) {
        Budget budget = budgetRepo.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Budget cannot be found"));
        budgetRepo.delete(budget);
    }

    private BudgetDTO convertToBudgetDTO(Budget budget) {
        return new BudgetDTO(
                budget.getId(),
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
                .orElseThrow(() -> new BudgetNotFoundException("Budget with ID - " + id + " not found"));
    }

}
