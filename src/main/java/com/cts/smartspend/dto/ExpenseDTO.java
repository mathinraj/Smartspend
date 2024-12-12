package com.cts.smartspend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount should be greater than O")
    @Digits(integer = 6, fraction = 2, message = "Amount must be a valid number")
    private Double amount;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "UserID is required")
    private Long userId;

    private boolean isRecurring;

    private String recurrenceInterval;
}
