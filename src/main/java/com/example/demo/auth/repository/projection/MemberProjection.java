package com.example.demo.auth.repository.projection;

import com.example.demo.auth.domain.type.MemberStatus;
import lombok.Builder;

import java.time.Instant;

public final class MemberProjection {
    private MemberProjection() {}

    @Builder
    public record MemberListViewProjection(
            String username,
            String fullName,
            MemberStatus status
    ) {}

    @Builder
    public record MemberDetailedViewProjection(
            String username,
            String fullName,
            MemberStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {}
}
