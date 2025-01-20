package com.cts.smartspend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
public class ExpenseDTO {

    private Long id;

    @NotNull(message = "Category ID should not be null")
    private Long categoryId;

    @DecimalMin(value = "1", message = "The amount should be greater than Rs.1")
    private Double amount;

    @NotBlank(message = "Provide proper description")
    private String description;

    @NotNull(message = "Date must be entered")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "User ID should not be null")
    private Long userId;

    public ExpenseDTO() {
    }

    public ExpenseDTO(Long id, Double amount, String description, Long categoryId, Long userId, LocalDate date) {
        this.id = id;
        this.categoryId = categoryId;
        this.amount = amount;
        this.description = description;
        this.userId = userId;
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
