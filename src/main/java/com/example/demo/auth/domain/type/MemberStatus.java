package com.example.demo.auth.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberStatus {
    @JsonProperty("pending")
    PENDING(false),
    @JsonProperty("active")
    ACTIVE(true),
    /** BLOCKED */
    @JsonProperty("suspended")
    SUSPENDED(false),
    @JsonProperty("protected")
    PROTECTED(false),
    @JsonProperty("sleep")
    SLEEP(false),
    @JsonProperty("removed")
    REMOVED(false);

    final boolean canSignIn; // <- 이처럼 코드 종속적인 관리를 어느 수준까지 허용할지 의사결정이 필요하다.
}
