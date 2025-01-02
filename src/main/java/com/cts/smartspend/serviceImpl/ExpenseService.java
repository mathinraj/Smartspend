package com.cts.smartspend.serviceImpl;
import com.cts.smartspend.dto.ExpenseResponseDTO;
import com.cts.smartspend.entity.Category;
import com.cts.smartspend.entity.Expense;
import com.cts.smartspend.entity.Budget;
import com.cts.smartspend.dto.ExpenseDTO;
import com.cts.smartspend.entity.User;
import com.cts.smartspend.exception.BudgetNotFoundException;
import com.cts.smartspend.exception.CategoryNotFoundException;
import com.cts.smartspend.exception.ExpenseNotFoundException;
import com.cts.smartspend.exception.UserNotFoundException;
import com.cts.smartspend.repo.CategoryRepo;
import com.cts.smartspend.repo.ExpenseRepo;
import com.cts.smartspend.repo.BudgetRepo;
import com.cts.smartspend.repo.UserRepo;
import com.cts.smartspend.service.IExpenseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService implements IExpenseService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private BudgetRepo budgetRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Category category = categoryRepo.findById(expenseDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        User user = userRepo.findById(expenseDTO.getUserId())
                        .orElseThrow(() -> new UserNotFoundException("User not found"));

        Expense expense = new Expense();
        expense.setCategory(category);
        expense.setUser(user);
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());

        Expense savedExpense = expenseRepo.save(expense);
        return convertToExpenseDTO(savedExpense);

    }

    @Override
    public List<ExpenseResponseDTO> getAllExpenses() {
        List<Expense> expenses= expenseRepo.findAll();
        if (expenses.isEmpty()) {
            throw new ExpenseNotFoundException("Expense not found");
        }
        return expenses.stream()
                .map(this::convertToExpenseResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseResponseDTO getExpenseById(Long id) {
         Expense expense = expenseRepo.findById(id)
                 .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + id));
         return convertToExpenseResponseDTO(expense);
    }

    @Override
    public List<ExpenseResponseDTO> getExpenseByCategory(Long id) {
        if (!categoryRepo.existsById(id)) {
            throw new CategoryNotFoundException("Category not found with id - " +id);
        }
        List<Expense> expenses = expenseRepo.findByCategoryId(id);
        if (expenses.isEmpty()) {
            throw new ExpenseNotFoundException("No expenses found for category id - " + id);
        }
        return expenses.stream()
                .map(this::convertToExpenseResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpenseResponseDTO> getExpensesByDate(LocalDate date) {
        List<Expense> expenses = expenseRepo.findByDate(date);
        if (expenses.isEmpty()) {
            throw new ExpenseNotFoundException("No expenses found on " + date);
        }
        return expenses.stream()
                .map(this::convertToExpenseResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpenseResponseDTO> getExpensesByRange(LocalDate startDate, LocalDate endDate) {
        List<Expense> expenses = expenseRepo.findByDateRange(startDate, endDate);
        if (expenses.isEmpty()) {
            throw new ExpenseNotFoundException("No expenses found between " +startDate + " and " + endDate );
        }
        return expenses.stream()
                .map(this::convertToExpenseResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {
        Expense expense = getExpenseEntityById(id);

        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        Expense savedExpense =  expenseRepo.save(expense);
        return convertToExpenseDTO(savedExpense);
    }

    @Override
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
                expense.getDescription(),
                expense.getUser().getId(),
                expense.getDate()
        );
    }

    private ExpenseResponseDTO convertToExpenseResponseDTO(Expense expense) {
        // Find the budget that covers the expense date
        Optional<Budget> budgetOpt = budgetRepo.findBudgetByCategoryAndDate(
                expense.getCategory().getId(), expense.getDate());

        double value = 0;
        String remainingBudget = "Budget is not created yet";

        if (budgetOpt.isPresent()) {
            Budget budget = budgetOpt.get();
            double totalExpenses = expenseRepo.findByCategoryIdAndDateRange(
                            expense.getCategory().getId(), budget.getStartDate(), budget.getEndDate())
                    .stream()
                    .mapToDouble(Expense::getAmount)
                    .sum();

            value = budget.getAmount() - totalExpenses;
        }

        if (value != 0) {
            remainingBudget = Double.toString(value);
        }

        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getDate(),
                expense.getCategory().getName(),
                expense.getUser().getId(),
                remainingBudget
        );
    }
}

