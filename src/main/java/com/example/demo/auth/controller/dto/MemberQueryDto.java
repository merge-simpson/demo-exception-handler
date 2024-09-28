package com.example.demo.auth.controller.dto;

import com.example.demo.auth.readmodel.MemberReadModel.MemberSummaryReadModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;

import java.util.List;

public record MemberQueryDto() {
    @Builder
    public record MemberQueryAllResponseDto(
            @JsonInclude(Include.NON_NULL) List<MemberSummaryReadModel> members,
            @JsonInclude(Include.NON_NULL) Integer lastPage,
            @JsonInclude(Include.NON_NULL) Long total
    ) {
    }
}
