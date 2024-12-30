package com.cts.smartspend.dto;

import java.time.LocalDate;

public class ExpenseResponseDTO {
    private Long id;
    private String description;
    private Double amount;
    private LocalDate date;
    private String categoryName;
    private Long userId;
    private String remainingBudget;

    public ExpenseResponseDTO() {
    }

    public ExpenseResponseDTO(Long id, String description, Double amount, LocalDate date, String categoryName, Long userId, String remainingBudget) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.categoryName = categoryName;
        this.userId = userId;
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

    public String getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(String remainingBudget) {
        this.remainingBudget = remainingBudget;
    }
}
