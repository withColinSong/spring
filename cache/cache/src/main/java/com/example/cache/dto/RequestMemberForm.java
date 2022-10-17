package com.example.cache.dto;

import com.example.cache.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class RequestMemberForm {

    private final String name;
    private final Integer age;

    public Member toEntity() {
       return Member.builder()
                .name(name)
                .age(age)
               .build();
    }

}
