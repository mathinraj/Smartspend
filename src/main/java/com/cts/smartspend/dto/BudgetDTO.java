package com.cts.smartspend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDTO {

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Limit amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount should be greater than O")
    private Double limit;

    @NotNull(message = "UserID is required")
    private Long userID;
}
