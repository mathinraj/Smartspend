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
}
