package com.cts.smartspend.controller;

import com.cts.smartspend.dto.DateDTO;
import com.cts.smartspend.dto.DateRangeDTO;
import com.cts.smartspend.dto.ExpenseDTO;
import com.cts.smartspend.dto.ExpenseResponseDTO;
import com.cts.smartspend.service.ExpenseService;
import jakarta.validation.Valid;
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

    @PostMapping("/add")
    public ResponseEntity<ExpenseDTO> createExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {
        ExpenseDTO expense = expenseService.createExpense(expenseDTO);
        return new ResponseEntity<>(expense,HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses() {
        List<ExpenseResponseDTO> expenses = expenseService.getAllExpenses();
        return new ResponseEntity<>(expenses,HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable Long id) {
        ExpenseResponseDTO expense = expenseService.getExpenseById(id);
        return new ResponseEntity<>(expense,HttpStatus.OK);
    }

    @GetMapping("/get/category/{id}")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpenseByCategory(@PathVariable Long id) {
        List<ExpenseResponseDTO> expense = expenseService.getExpenseByCategory(id);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @GetMapping("/get/date")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpensesByDate(@RequestBody DateDTO dateDTO) {
        List<ExpenseResponseDTO> expense = expenseService.getExpensesByDate(dateDTO.getDate());
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @GetMapping("/get/range")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpensesByRange(@RequestBody DateRangeDTO dateRange) {
        List<ExpenseResponseDTO> expense = expenseService.getExpensesByRange(dateRange.getStartDate(), dateRange.getEndDate());
        return new ResponseEntity<>(expense,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseDTO expenseDTO) {
        ExpenseDTO expense = expenseService.updateExpense(id, expenseDTO);
        return new ResponseEntity<>(expense,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return new ResponseEntity<>("Expense deleted successfully", HttpStatus.OK);  // Return NO_CONTENT status after deletion
    }

}
