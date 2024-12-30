package com.cts.smartspend.repo;

import com.cts.smartspend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByRole(User.Role role);
}
