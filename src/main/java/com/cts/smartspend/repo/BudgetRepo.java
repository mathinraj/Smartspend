package com.cts.smartspend.repo;

import com.cts.smartspend.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BudgetRepo extends JpaRepository<Budget, Long> {

    List<Budget> findAllByCategoryId(Long id);

    Optional<Budget> findByCategoryId(Long categoryId);

}
