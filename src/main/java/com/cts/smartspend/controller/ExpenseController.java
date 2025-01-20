package com.cts.smartspend.controller;

import com.cts.smartspend.dto.DateDTO;
import com.cts.smartspend.dto.DateRangeDTO;
import com.cts.smartspend.dto.ExpenseDTO;
import com.cts.smartspend.dto.ExpenseResponseDTO;
import com.cts.smartspend.exception.CategoryNotFoundException;
import com.cts.smartspend.exception.ExpenseNotFoundException;
import com.cts.smartspend.service.IExpenseService;
import com.cts.smartspend.serviceImpl.ExpenseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    IExpenseService expenseService;

   private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping("/add")
    public ResponseEntity<ExpenseDTO> createExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {
        ExpenseDTO expense = expenseService.createExpense(expenseDTO);
        logger.info("Expense created successfully");
        return new ResponseEntity<>(expense,HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/get/all")
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses() {
        List<ExpenseResponseDTO> expenses = expenseService.getAllExpenses();
        logger.info("Expenses found");
        return new ResponseEntity<>(expenses,HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable Long id) {
        ExpenseResponseDTO expense = expenseService.getExpenseById(id);
        logger.info("Expense found with ID {}", id);
        return new ResponseEntity<>(expense,HttpStatus.OK);
    }

    @GetMapping("/get/category/{id}")
    public ResponseEntity<?> getExpenseByCategory(@PathVariable Long id) {
            List<ExpenseResponseDTO> expenses = expenseService.getExpenseByCategory(id);
            logger.info("Expense found with category id: " + id);
            return new ResponseEntity<>(expenses,HttpStatus.OK);
    }

    @GetMapping("/get/date")
    public ResponseEntity<?> getExpensesByDate(@RequestBody DateDTO dateDTO) {
            List<ExpenseResponseDTO> expense = expenseService.getExpensesByDate(dateDTO.getDate());
            logger.info("Expenses found on date {}", dateDTO.getDate());
            return new ResponseEntity<>(expense, HttpStatus.OK);
        }

    @GetMapping("/get/range")
    public ResponseEntity<?> getExpensesByRange(@RequestBody DateRangeDTO dateRange) {
        if (dateRange.getEndDate().isBefore(dateRange.getStartDate())) {
            logger.warn("End date is before the start date");
            return new ResponseEntity<>("End date should be after start date", HttpStatus.BAD_REQUEST);
        }
        List<ExpenseResponseDTO> expense = expenseService.getExpensesByRange(dateRange.getStartDate(), dateRange.getEndDate());
        logger.info("Expenses found with date range {}", dateRange.getStartDate() + " - " + dateRange.getEndDate());
        return new ResponseEntity<>(expense,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseDTO expenseDTO) {
            ExpenseDTO expense = expenseService.updateExpense(id, expenseDTO);
            logger.info("Expense updated successfully");
            return new ResponseEntity<>(expense,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        logger.info("Expense deleted successfully");
        return new ResponseEntity<>("Expense deleted successfully", HttpStatus.OK);  // Return NO_CONTENT status after deletion
    }
}
