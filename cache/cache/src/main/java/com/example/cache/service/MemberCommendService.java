package com.example.cache.service;

import com.example.cache.domain.Member;
import com.example.cache.dto.RequestMemberForm;
import com.example.cache.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberCommendService {

    private final MemberRepository memberRepository;

    public void MemberSave(RequestMemberForm form) {
        memberRepository.save(form.toEntity());
    }
}
