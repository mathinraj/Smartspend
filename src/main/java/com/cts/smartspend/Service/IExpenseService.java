package com.cts.smartspend.service;

import com.cts.smartspend.dto.ExpenseDTO;
import com.cts.smartspend.dto.ExpenseResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface IExpenseService {
    ExpenseDTO createExpense(ExpenseDTO expenseDTO);

    List<ExpenseResponseDTO> getAllExpenses();

    ExpenseResponseDTO getExpenseById(Long id);

    List<ExpenseResponseDTO> getExpenseByCategory(Long id);

    List<ExpenseResponseDTO> getExpensesByDate(LocalDate date);

    List<ExpenseResponseDTO> getExpensesByRange(LocalDate startDate, LocalDate endDate);

    ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO);

    void deleteExpense(Long id);
}
