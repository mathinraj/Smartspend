package com.cts.smartspend.service;
import com.cts.smartspend.dto.ExpenseResponseDTO;
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
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ExpenseRepo expenseRepo;

    @Transactional
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Category category = categoryRepo.findById(expenseDTO.getCategoryId())
                .orElseThrow(() -> new ExpenseNotFoundException("Category not found"));

        Expense expense = new Expense();
        expense.setCategory(category);
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        Expense savedExpense =  expenseRepo.save(expense);
        return convertToExpenseDTO(savedExpense);
    }

    public List<ExpenseResponseDTO> getAllExpenses() {
        List<Expense> expenses= expenseRepo.findAll();
        return expenses.stream()
                .map(this::convertToExpenseResponseDTO)
                .collect(Collectors.toList());
    }

    public ExpenseResponseDTO getExpenseById(Long id) {
         Expense expense = expenseRepo.findById(id)
                 .orElseThrow(() -> new ExpenseNotFoundException("Expense with ID " + id + " not found"));
         return convertToExpenseResponseDTO(expense);
    }

    public List<ExpenseResponseDTO> getExpenseByCategory(Long id) {
        List<Expense> expenses = expenseRepo.findByCategoryId(id);
        return expenses.stream()
                .map(this::convertToExpenseResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ExpenseResponseDTO> getExpensesByDate(LocalDate date) {
        List<Expense> expenses = expenseRepo.findByDate(date);
        return expenses.stream()
                .map(this::convertToExpenseResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ExpenseResponseDTO> getExpensesByRange(LocalDate startDate, LocalDate endDate) {
        List<Expense> expenses = expenseRepo.findByDateRange(startDate, endDate);
        return expenses.stream()
                .map(this::convertToExpenseResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {
        Expense expense = getExpenseEntityById(id);

        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        Expense savedExpense =  expenseRepo.save(expense);
        return convertToExpenseDTO(savedExpense);
    }

    @Transactional
    public void deleteExpense(Long id) {
        Expense expense = expenseRepo.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense with ID " + id + " not found"));
        expenseRepo.delete(expense);
    }

    private Expense getExpenseEntityById(Long id) {
        return expenseRepo.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense with ID " + id + " not found"));
    }
    private ExpenseDTO convertToExpenseDTO(Expense expense) {
        return new ExpenseDTO(
                expense.getId(),
                expense.getCategory().getId(),
                expense.getAmount(),
                expense.getDate(),
                expense.getDescription()

        );
    }

    private ExpenseResponseDTO convertToExpenseResponseDTO(Expense expense) {
        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getDate(),
                expense.getCategory().getName()
        );
    }

}
