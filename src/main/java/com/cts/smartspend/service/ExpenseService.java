package com.cts.smartspend.service;
import com.cts.smartspend.entity.Expense;
import com.cts.smartspend.entity.User;
import com.cts.smartspend.dto.ExpenseDTO;
import com.cts.smartspend.repo.ExpenseRepo;
import com.cts.smartspend.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepo expenseRepo;
    private final UserRepo userRepo;

    public ExpenseService(ExpenseRepo expenseRepo, UserRepo userRepo) {
        this.expenseRepo = expenseRepo;
        this.userRepo = userRepo;
    }


    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll().stream().map(expense -> new
                ExpenseDTO(
                        expense.getTitle(),
                        expense.getAmount(),
                        expense.getDate(),
                        expense.getCategory(),
                        expense.getUser().getId(),
                        expense.isRecurring(),
                        expense.getRecurrenceInterval()
                ))
                .collect(Collectors.toList());
    }

    public void createExpense(Expense expense) {
        User user = userRepo.findById(expenseDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = new Expense();
        expense.setTitle(expense.getTitle());
        expense.setAmount(expense.getAmount());
        expense.setDate(expense.getDate());
        expense.setCategory(expense.getCategory());
        expense.setUser(expense.getUser());
        expense.setRecurring(expense.isRecurring());
        expense.setRecurrenceInterval(expense.getRecurrenceInterval());
        expenseRepo.save(expense);
    }

    public void updateExpense(Long id, ExpenseDTO expenseDTO) {
        Expense expense = expenseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setTitle(expenseDTO.getTitle());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        expense.setCategory(expenseDTO.getCategory());
        expense.setRecurring(expenseDTO.isRecurring());
        expense.setRecurrenceInterval(expenseDTO.getRecurrenceInterval());
        expenseRepo.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepo.deleteById(id);
    }
}
