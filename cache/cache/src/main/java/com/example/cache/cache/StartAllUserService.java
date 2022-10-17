package com.example.cache.cache;

import com.example.cache.domain.Member;
import com.example.cache.repository.MemberRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StartAllUserService {

    private final MemberRepository memberRepository;

    public StartAllUserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        member();
    }

    @Cacheable(value = "member-all")
    public List<Member> member() {
        return memberRepository.findAll();
    }
}
