package com.example.demo.auth.repository.projection;

import com.example.demo.auth.domain.data.MemberStatus;
import lombok.Builder;

import java.time.Instant;

public final class MemberProjection {
    private MemberProjection() {}

    @Builder
    public record SigningProjection(
            String password,
            MemberStatus status
    ) {}

    @Builder
    public record MemberSummaryProjection(
            String username,
            String fullName,
            MemberStatus status
    ) {}

    @Builder
    public record MemberDetailsProjection(
            String username,
            String fullName,
            MemberStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {}
}
