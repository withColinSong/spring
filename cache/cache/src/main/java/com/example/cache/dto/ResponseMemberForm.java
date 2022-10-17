package com.example.cache.dto;

import com.example.cache.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
public class ResponseMemberForm {

    private String name;

    @Builder
    public ResponseMemberForm(String name) {
        this.name = name;
    }

    public static ResponseMemberForm toDto(Member member) {
        return ResponseMemberForm.builder()
                .name(member.getName())
                .build();
    }

}
