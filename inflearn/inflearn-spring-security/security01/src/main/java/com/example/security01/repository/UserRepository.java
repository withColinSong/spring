package com.example.security01.repository;

import com.example.security01.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD함수를 JpaRepository 가지고 있다.
// @Repository라는 어노테이션이 없어도 IoC 가능.  JpaRepository를 상속받았기 때문에
public interface UserRepository extends JpaRepository<User, Integer> {

    // findBy 규칙 => Username 문법
    // select * from user where username = ?
    public User findByUsername(String username);
}
