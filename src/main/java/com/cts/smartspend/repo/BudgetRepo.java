package com.cts.smartspend.repo;

import com.cts.smartspend.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface BudgetRepo extends JpaRepository<Budget, Long> {

    List<Budget> findAllByCategoryId(Long id);

    Optional<Budget> findByCategoryId(Long categoryId);

    @Query("SELECT b FROM Budget b WHERE b.category.id = :categoryId AND " +
            "((b.startDate BETWEEN :startDate AND :endDate) OR " +
            "(b.endDate BETWEEN :startDate AND :endDate) OR " +
            "(b.startDate <= :startDate AND b.endDate >= :endDate))")
    List<Budget> findOverlappingBudgets(@Param("categoryId") Long categoryId,
                                        @Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate);

    @Query("SELECT b FROM Budget b WHERE b.category.id = :categoryId AND :date BETWEEN b.startDate AND b.endDate")
    Optional<Budget> findBudgetByCategoryAndDate(@Param("categoryId") Long categoryId, @Param("date") LocalDate date);
}
