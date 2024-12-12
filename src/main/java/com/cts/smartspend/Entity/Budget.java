package com.cts.smartspend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "budgets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String Category;

    @Column(nullable = false)
    private Double limit;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
