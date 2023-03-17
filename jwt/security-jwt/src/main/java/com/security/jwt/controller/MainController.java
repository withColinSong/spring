package com.security.jwt.controller;

import com.security.jwt.config.TokenProvider;
import com.security.jwt.dto.LoginDto;
import com.security.jwt.entity.User;
import com.security.jwt.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.web.bind.annotation.*;

/**
 * NOTE
 * @PreAuthorize("hasRole('ROLE_ADMIN')"): default가 ROLE_권한자로 시작된다.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final UserQueryRepository userQueryRepository;
    private final TokenProvider tokenProvider;

    @GetMapping("/health")
    public String health() {
        return "";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        User user = userQueryRepository.fndByUser(loginDto.getEmail(), loginDto.getPassword());
        String accessToken = tokenProvider.createAccessToken(user.getRoleType().toString(), user.getEmail());
        return ResponseEntity.ok(accessToken);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@AuthenticationPrincipal Jwt jwt, Authentication authentication){
        String format = String.format("Hello, %s!", jwt.getClaims());
        return ResponseEntity.ok(format);
    }

    @PostMapping("/users")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> userTest(@AuthenticationPrincipal Jwt jwt,
                                      Authentication authentication) {
        log.info("name={}", authentication.getAuthorities());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> admin(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok().build();
    }

}
