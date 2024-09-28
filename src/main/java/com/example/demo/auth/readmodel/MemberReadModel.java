package com.example.demo.auth.readmodel;

import com.example.demo.auth.domain.data.MemberStatus;
import lombok.Builder;

import java.time.Instant;

public final class MemberReadModel {
    private MemberReadModel() {}

    @Builder
    public record SigningReadModel(
            String password,
            MemberStatus status
    ) {}

    @Builder
    public record MemberSummaryReadModel(
            String username,
            String fullName,
            MemberStatus status
    ) {}

    @Builder
    public record MemberDetailsReadModel(
            String username,
            String fullName,
            MemberStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {}
}
