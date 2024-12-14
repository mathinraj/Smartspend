package com.cts.smartspend.service;
import com.cts.smartspend.entity.Category;
import com.cts.smartspend.entity.Expense;
import com.cts.smartspend.dto.ExpenseDTO;
import com.cts.smartspend.exception.ExpenseNotFoundException;
import com.cts.smartspend.repo.CategoryRepo;
import com.cts.smartspend.repo.ExpenseRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ExpenseRepo expenseRepo;

    @Transactional
    public Expense createExpense(ExpenseDTO expenseDTO) {
        Category category = categoryRepo.findById(expenseDTO.getCategoryId())
                .orElseThrow(() -> new ExpenseNotFoundException("Category not found"));

        Expense expense = new Expense();
        expense.setCategory(category);
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        return expenseRepo.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepo.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense with ID " + id + " not found"));
    }

    public List<Expense> getExpenseByCategory(Long id) {
        return expenseRepo.findByCategoryId(id);
    }

    public List<Expense> getExpensesByDate(LocalDate date) {
        return expenseRepo.findByDate(date);
    }

    public List<Expense> getExpensesByRange(LocalDate startDate, LocalDate endDate) {
        return expenseRepo.findByDateRange(startDate, endDate);
    }

    @Transactional
    public Expense updateExpense(Long id, ExpenseDTO expenseDTO) {
        Expense expense = getExpenseById(id);

        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        return expenseRepo.save(expense);
    }

    @Transactional
    public void deleteExpense(Long id) {
        Expense expense = expenseRepo.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense with ID " + id + " not found"));
        expenseRepo.delete(expense);
    }
}
