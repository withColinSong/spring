package com.song.jwt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class MainController {

    @GetMapping("/health")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<?> auth(Authentication authentication,
                                  @RequestHeader Map<String, String> headers,
                                  @RequestHeader("user-agent") String userAgent) {

        for (Map.Entry<String, String> entry : headers.entrySet()) {
           log.info("key: " + entry.getKey() + ", value: " + entry.getValue());
        }
        return ResponseEntity.ok(authentication.getName() + "!");
    }

}
