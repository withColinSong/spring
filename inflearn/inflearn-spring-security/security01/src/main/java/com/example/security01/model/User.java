package com.example.security01.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String roles; //ROLE_USER, ROLE_ADMIN
    private String provider;
    private String picture;
    @CreationTimestamp
    private Timestamp createDate;

    @Builder
    public User(String username, String email, String roles, String provider, String picture, Timestamp createDate) {
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.provider = provider;
        this.picture = picture;
        this.createDate = createDate;
    }
}
