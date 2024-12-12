package com.cts.smartspend.controller;

import com.cts.smartspend.entity.Expense;
import com.cts.smartspend.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        return ResponseEntity.created(expenseService.createExpense(expense));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable int id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @GetMapping("/get/category/{id}")
    public ResponseEntity<List<Expense>> getExpenseByCategory(@PathVariable int id) {
        return ResponseEntity.ok(expenseService.getExpenseByCategory(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable int id, @RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.updateExpense(id, Expense));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable int id) {
        return ResponseEntity.ok(expenseService.deleteExpense(id));
    }
}
