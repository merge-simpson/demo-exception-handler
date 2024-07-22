package com.example.demo.auth.domain;

import com.example.common.jpa.support.UuidBaseEntity;
import com.example.demo.auth.domain.type.MemberStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class Member extends UuidBaseEntity {
    public String username;
    public String password;

    public String fullName;

    @Enumerated(EnumType.STRING)
    public MemberStatus status;

    private Instant createdAt;
    private Instant updatedAt;
}
