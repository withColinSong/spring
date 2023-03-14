package com.security.jwt.repository;

import com.security.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
   <R> R findByEmail(String email);
}
