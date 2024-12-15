package com.cts.smartspend.dto;

import com.cts.smartspend.entity.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDTO {
    public BudgetDTO() {
    }

    @DecimalMin(value = "1.0", message = "Budget amount should be greater than Rs.1")
    private double amount;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;

    @NotBlank(message = "Start date cannot be empty")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @NotBlank(message = "End date cannot be empty")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    public BudgetDTO(double amount, Long categoryId, LocalDate startDate, LocalDate endDate) {
        this.amount = amount;
        this.categoryId = categoryId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
