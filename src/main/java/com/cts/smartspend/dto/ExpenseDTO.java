package com.cts.smartspend.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {

    private Long id;

    @NotNull(message = "Category ID should not be null")
    private Long categoryId;

    @DecimalMin(value = "1", message = "The amount should be greater than Rs.1")
    private double amount;

    @NotBlank(message = "Provide proper description")
    private String description;

    @NotNull(message = "Date must be entered")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    public ExpenseDTO() {
    }

    public ExpenseDTO(Long id, Long categoryId, double amount, String description, LocalDate date) {
        this.id = id;
        this.categoryId = categoryId;
        this.amount = amount;
        this.description = description;
        this.date = date;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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
