package com.example.demo.auth.api.dto;

import com.example.demo.auth.domain.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;

import java.util.List;

public record MemberQueryDto() {
    @Builder
    public record MemberQueryAllResponseDto(
            @JsonInclude(Include.NON_EMPTY) List<Member> members,
            @JsonInclude(Include.NON_DEFAULT) Integer lastPage,
            @JsonInclude(Include.NON_NULL) Long total
    ) {
    }
}
