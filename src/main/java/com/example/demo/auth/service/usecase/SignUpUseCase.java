package com.example.demo.auth.service.usecase;

import com.example.demo.auth.controller.dto.AuthDto.MemberSignUpRequestDto;
import com.example.demo.auth.domain.Member;
import com.example.demo.auth.domain.data.MemberStatus;

import java.time.Instant;

public interface SignUpUseCase {
    Member signUp(Member member);
    Member signUp(MemberSignUpRequestDto dto, MemberStatus status, Instant createdAt);
}
