package com.cts.smartspend.controller;

import com.cts.smartspend.dto.BudgetDTO;
import com.cts.smartspend.entity.Budget;
import com.cts.smartspend.service.BudgetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/getall")
    public ResponseEntity<List<Budget>> getAllBudgets(){
        return ResponseEntity.ok(budgetService.getAllBudgets());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id){
        return ResponseEntity.ok(budgetService.getBudgetById(id));
    }

    @PostMapping("/set")
    public ResponseEntity<String> setBudget(@Valid @RequestBody BudgetDTO budgetDTO){
        budgetService.setBudget(budgetDTO);
        return ResponseEntity.ok("Budget set successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBudget(@Valid @RequestBody BudgetDTO budgetDTO, @PathVariable Long id){
        budgetService.updateBudget(id, budgetDTO);
        return ResponseEntity.ok("Budget updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBudget(@PathVariable Long id){
        budgetService.deleteBudget(id);
        return ResponseEntity.ok("Budget deleted successfully");}
}
