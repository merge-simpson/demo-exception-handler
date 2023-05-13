package com.example.demo.members.domain;

import com.example.demo.members.domain.type.MemberStatus;
import com.example.demo.web.support.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Entity;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@Entity
public final class Member extends BaseEntity {

    @JsonInclude(Include.NON_EMPTY)
    public String username;
    @JsonInclude(Include.NON_EMPTY)
    public String name;
    @JsonInclude(Include.NON_NULL)
    public Integer age;
    @JsonInclude(Include.NON_NULL)
    @Builder.Default
    public MemberStatus status = MemberStatus.PENDING;
    @JsonInclude(Include.NON_NULL)
    @Builder.Default
    public OffsetDateTime joinDate = OffsetDateTime.now(ZoneId.of("Asia/Seoul"));
}
