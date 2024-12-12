package com.cts.smartspend.controller;

import com.cts.smartspend.dto.BudgetDTO;
import com.cts.smartspend.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public ResponseEntity<List<BudgetDTO>> getAllBudgets(){
        return ResponseEntity.ok(budgetService.getAllBudgets());
    }

    @PostMapping("/set")
    public ResponseEntity<String> setBudget(@RequestBody BudgetDTO budgetDTO){
        budgetService.setBudget(budgetDTO);
        return ResponseEntity.ok("Budget set successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBudget(@RequestBody BudgetDTO budgetDTO, @PathVariable Long id){
        budgetService.updateBudget(id, budgetDTO);
        return ResponseEntity.ok("Budget updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBudget(@PathVariable Long id){
        return ResponseEntity.ok(budgetService.deleteBudget(id),"Budget deleted successfully");
    }
}
