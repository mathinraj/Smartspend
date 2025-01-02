package com.cts.smartspend.controller;

import com.cts.smartspend.dto.BudgetDTO;
import com.cts.smartspend.service.IBudgetService;
import com.cts.smartspend.serviceImpl.BudgetService;
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
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private IBudgetService budgetService;

    private static final Logger logger = LoggerFactory.getLogger(BudgetController.class);

    @GetMapping("/get/all")
    public ResponseEntity<List<BudgetDTO>> getAllBudgets(){
        List<BudgetDTO> budget = budgetService.getAllBudgets();
        logger.info("Budget found");
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BudgetDTO> getBudgetById(@PathVariable Long id){
        BudgetDTO budgetDTO = budgetService.getBudgetById(id);
        logger.info("Budget found with ID {}", id);
        return new ResponseEntity<>(budgetDTO, HttpStatus.OK);
    }

    @GetMapping("/get/category/{id}")
    public ResponseEntity<List<BudgetDTO>> getBudgetByCategory(@PathVariable Long id){
        List<BudgetDTO> budgetDTO = budgetService.getBudgetByCategoryId(id);
        logger.info("Budget found with category ID {}", id);
        return new ResponseEntity<>(budgetDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/set")
    public ResponseEntity<?> setBudget(@Valid @RequestBody BudgetDTO budgetDTO){
        if (budgetDTO.getEndDate().isBefore(budgetDTO.getStartDate())) {
            logger.warn("End date should be after start date");
            return new ResponseEntity<>("End date should be after start date", HttpStatus.BAD_REQUEST);
        }
        BudgetDTO budget = budgetService.setBudget(budgetDTO);
        logger.info("Budget created successfully");
        return new ResponseEntity<>(budget, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<BudgetDTO> updateBudget(@Valid @RequestBody BudgetDTO budgetDTO, @PathVariable Long id){
        BudgetDTO budget = budgetService.updateBudget(id, budgetDTO);
        logger.info("Budget updated successfully");
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBudget(@PathVariable Long id){
        budgetService.deleteBudget(id);
        logger.info("Budget deleted successfully");
        return new ResponseEntity<>("Budget deleted successfully", HttpStatus.OK);}
}
