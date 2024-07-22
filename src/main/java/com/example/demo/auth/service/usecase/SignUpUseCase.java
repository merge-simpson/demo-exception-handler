package com.example.demo.auth.service.usecase;

import com.example.demo.auth.api.dto.MemberCommandDto.MemberSignUpRequestDto;
import com.example.demo.auth.domain.Member;
import com.example.demo.auth.domain.type.MemberStatus;

public interface SignUpUseCase {
    Member signUp(Member member);
    Member signUp(MemberSignUpRequestDto dto, MemberStatus status);
}
