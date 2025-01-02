package com.cts.smartspend.dto;

import com.cts.smartspend.entity.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Long id;

    @DecimalMin(value = "1.0", message = "Budget amount should be greater than Rs.1")
    private double amount;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;

    @NotNull(message = "Start date cannot be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public BudgetDTO(Long id, double amount, Long categoryId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.amount = amount;
        this.categoryId = categoryId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BudgetDTO() {}
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }


    
}
