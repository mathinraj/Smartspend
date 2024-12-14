package com.cts.smartspend.repo;

import com.cts.smartspend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository <Category, Long>{
}
