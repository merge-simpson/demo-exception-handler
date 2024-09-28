package com.example.demo.auth.domain.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum MemberStatus {
    @JsonProperty("pending")
    PENDING(10),
    @JsonProperty("active")
    ACTIVE(20),
    /** BLOCKED */
    @JsonProperty("suspended")
    SUSPENDED(30),
    @JsonProperty("protected")
    PROTECTED(40),
    @JsonProperty("removed")
    REMOVED(50);

    static {
        // enum은 열거 상수의 생성자 실행 후 -> static 블록
        assert isUidUnique() : "MemberStatus의 모든 UID는 고유해야 합니다.";
    }

    private final int uid;

    private static boolean isUidUnique() {
        Set<Integer> uidSet = Arrays.stream(values())
                .map(MemberStatus::uid)
                .collect(Collectors.toSet());

        return uidSet.size() == values().length;
    }

    public int uid() {
        return uid;
    }
}
