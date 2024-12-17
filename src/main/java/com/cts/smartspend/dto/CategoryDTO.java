package com.cts.smartspend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Category name should not be empty")
    private String name;

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO() {}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getName(){return name;}
    public void setName(String name) {this.name = name;}
}
