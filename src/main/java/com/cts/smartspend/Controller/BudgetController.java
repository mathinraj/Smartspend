package com.cts.smartspend.controller;

import com.cts.smartspend.dto.BudgetDTO;
import com.cts.smartspend.entity.Budget;
import com.cts.smartspend.service.BudgetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/getall")
    public ResponseEntity<List<BudgetDTO>> getAllBudgets(){
        List<BudgetDTO> budget = budgetService.getAllBudgets();
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BudgetDTO> getBudgetById(@PathVariable Long id){
        BudgetDTO budgetDTO = budgetService.getBudgetById(id);
        return new ResponseEntity<>(budgetDTO, HttpStatus.OK);
    }

    @GetMapping("/get/category/{id}")
    public ResponseEntity<List<BudgetDTO>> getBudgetByCategory(@PathVariable Long id){
        List<BudgetDTO> budgetDTO = budgetService.getBudgetByCategoryId(id);
        return new ResponseEntity<>(budgetDTO, HttpStatus.OK);
    }

    @PostMapping("/set")
    public ResponseEntity<BudgetDTO> setBudget(@Valid @RequestBody BudgetDTO budgetDTO){
        BudgetDTO budget = budgetService.setBudget(budgetDTO);
        return new ResponseEntity<>(budget, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BudgetDTO> updateBudget(@Valid @RequestBody BudgetDTO budgetDTO, @PathVariable Long id){
        return new ResponseEntity<>(budgetService.updateBudget(id, budgetDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBudget(@PathVariable Long id){
        budgetService.deleteBudget(id);
        return new ResponseEntity<>("Budget deleted successfully", HttpStatus.OK);}
}
