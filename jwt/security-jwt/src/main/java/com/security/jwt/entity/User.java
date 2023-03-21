package com.security.jwt.entity;

import com.security.jwt.enums.RoleType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "USERS")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;

    public User() { }

    @Builder
    public User(String name, String email, String password, RoleType roleType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleType = roleType;
    }
}
