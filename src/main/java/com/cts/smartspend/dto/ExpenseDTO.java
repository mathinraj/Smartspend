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

}
