package com.cts.smartspend.controller;

import com.cts.smartspend.dto.DateDTO;
import com.cts.smartspend.dto.DateRangeDTO;
import com.cts.smartspend.dto.ExpenseDTO;
import com.cts.smartspend.dto.ExpenseResponseDTO;
import com.cts.smartspend.service.ExpenseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

   private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @PostMapping("/add")
    public ResponseEntity<ExpenseDTO> createExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {
        ExpenseDTO expense = expenseService.createExpense(expenseDTO);
        logger.info("Expense created successfully");
        return new ResponseEntity<>(expense,HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses() {
        List<ExpenseResponseDTO> expenses = expenseService.getAllExpenses();
        if (expenses.isEmpty()) {
            logger.info("No expenses found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Expenses found");
        return new ResponseEntity<>(expenses,HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable Long id) {
        ExpenseResponseDTO expense = expenseService.getExpenseById(id);
        if (expense == null) {
            logger.info("Expense with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Expense found with ID {}", id);
        return new ResponseEntity<>(expense,HttpStatus.OK);
    }

    @GetMapping("/get/category/{id}")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpenseByCategory(@PathVariable Long id) {
        List<ExpenseResponseDTO> expense = expenseService.getExpenseByCategory(id);
        if (expense == null) {
            logger.warn("Expense with category ID {} not found", id);
        }
        logger.info("Expenses found with category ID {}", id);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @GetMapping("/get/date")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpensesByDate(@RequestBody DateDTO dateDTO) {
        List<ExpenseResponseDTO> expense = expenseService.getExpensesByDate(dateDTO.getDate());
        if (expense == null) {
            logger.warn("Expenses with date {} not found", dateDTO.getDate());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Expenses found with date {}", dateDTO.getDate());
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @GetMapping("/get/range")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpensesByRange(@RequestBody DateRangeDTO dateRange) {
        List<ExpenseResponseDTO> expense = expenseService.getExpensesByRange(dateRange.getStartDate(), dateRange.getEndDate());
        if (expense == null) {
            logger.warn("Expenses with date range {} not found", dateRange.getStartDate() + " - " + dateRange.getEndDate());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Expenses found with date range {}", dateRange.getStartDate() + " - " + dateRange.getEndDate());
        return new ResponseEntity<>(expense,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseDTO expenseDTO) {
        ExpenseDTO expense = expenseService.updateExpense(id, expenseDTO);
        if (expense == null) {
            logger.warn("Expense with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Expense updated successfully");
        return new ResponseEntity<>(expense,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        logger.info("Expense deleted successfully");
        return new ResponseEntity<>("Expense deleted successfully", HttpStatus.OK);  // Return NO_CONTENT status after deletion
    }

}
