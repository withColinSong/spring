package com.example.cache.controller;

import com.example.cache.dto.RequestMemberForm;
import com.example.cache.dto.ResponseMemberForm;
import com.example.cache.service.MemberCommendService;
import com.example.cache.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommendService commendService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/save")
    public ResponseEntity<?> SaveUser(@RequestBody RequestMemberForm form) {
        commendService.MemberSave(form);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<?> SaveUser(@RequestParam Long id) {

        ResponseMemberForm user = null;

        try {
             user = memberQueryService.getUser(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/cache/delete")
    @CacheEvict(value = "user-id", allEntries = true)
    public ResponseEntity<?> CacheAlldelete() {
        return ResponseEntity.ok().build();
    }


}
