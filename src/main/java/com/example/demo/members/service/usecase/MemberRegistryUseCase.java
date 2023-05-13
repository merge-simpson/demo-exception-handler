package com.example.demo.members.service.usecase;

import com.example.demo.members.api.dto.MemberCommandDto.MemberSignUpRequestDto;
import com.example.demo.members.domain.Member;
import com.example.demo.members.domain.type.MemberStatus;

public interface MemberRegistryUseCase {
    Member signUp(Member member);
    Member signUp(MemberSignUpRequestDto dto, MemberStatus status);
}
