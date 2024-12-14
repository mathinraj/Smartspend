package com.cts.smartspend.controller;

import com.cts.smartspend.dto.DateRangeDTO;
import com.cts.smartspend.dto.ExpenseDTO;
import com.cts.smartspend.entity.Expense;
import com.cts.smartspend.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody ExpenseDTO expense) {
        return ResponseEntity.ok(expenseService.createExpense(expense));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @GetMapping("/get/category/{id}")
    public ResponseEntity<List<Expense>> getExpenseByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseByCategory(id));
    }

    @GetMapping("/get/date")
    public ResponseEntity<List<Expense>> getExpensesByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(expenseService.getExpensesByDate(date));
    }

    @GetMapping("/get/range")
    public ResponseEntity<List<Expense>> getExpensesByRange(@RequestBody DateRangeDTO dateRange) {
        return ResponseEntity.ok(expenseService.getExpensesByRange(dateRange.getStartDate(), dateRange.getEndDate()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseDTO expense) {
        return ResponseEntity.ok(expenseService.updateExpense(id, expense));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();  // Return NO_CONTENT status after deletion
    }

}
