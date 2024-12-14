package com.cts.smartspend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    @NotBlank(message = "Category name should not be empty")
    private String name;
}
