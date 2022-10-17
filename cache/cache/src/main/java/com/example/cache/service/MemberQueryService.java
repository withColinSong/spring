package com.example.cache.service;

import com.example.cache.domain.Member;
import com.example.cache.dto.ResponseMemberForm;
import com.example.cache.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;

    @Cacheable(value = "user-id")
    public ResponseMemberForm getUser(Long id) {
        log.info("db select userId={}", id);
        Optional<Member> byId = memberRepository.findById(id);
        return ResponseMemberForm.toDto(byId.orElseThrow());
    }

}
