package com.cts.smartspend.repo;

import com.cts.smartspend.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepo extends JpaRepository<Budget, Long> {
}
