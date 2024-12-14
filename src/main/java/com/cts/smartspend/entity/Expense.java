package com.cts.smartspend.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Category category;

    private double amount;

    private String description;

    private LocalDate date;
}
