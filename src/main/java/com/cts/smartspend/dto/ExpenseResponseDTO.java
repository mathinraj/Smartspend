package com.cts.smartspend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResponseDTO {
    private Long id;
    private String description;
    private Double amount;
    private LocalDate date;
    private String categoryName;
    private Double remainingBudget;

    public ExpenseResponseDTO() {
    }

    public ExpenseResponseDTO(Long id, String description, Double amount, LocalDate date, String categoryName, Double remainingBudget) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.categoryName = categoryName;
        this.remainingBudget = remainingBudget;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(Double remainingBudget) {
        this.remainingBudget = remainingBudget;
    }
}
