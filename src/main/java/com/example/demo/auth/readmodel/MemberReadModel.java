package com.example.demo.auth.readmodel;

import com.example.demo.auth.domain.type.MemberStatus;
import lombok.Builder;

import java.time.Instant;

public final class MemberReadModel {
    private MemberReadModel() {}

    @Builder
    public record MemberListViewReadModel(
            String username,
            String fullName,
            MemberStatus status
    ) {}

    @Builder
    public record MemberDetailedViewReadModel(
            String username,
            String fullName,
            MemberStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {}
}
