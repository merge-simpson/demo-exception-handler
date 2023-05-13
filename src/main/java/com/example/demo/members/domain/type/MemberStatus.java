package com.example.demo.members.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberStatus {
    PENDING(false),
    ACTIVE(true),
    /** BLOCKED */
    SUSPENDED(false),
    PROTECTED(false),
    SLEEP(false),
    REMOVED(false);

    final boolean canSignIn; // <- 이처럼 코드 종속적인 관리를 어느 수준까지 허용할지 의사결정이 필요하다.
}
