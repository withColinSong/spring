package com.security.jwt.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
