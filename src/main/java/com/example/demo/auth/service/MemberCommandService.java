package com.example.demo.auth.service;

import com.example.demo.auth.api.dto.MemberCommandDto.MemberSignUpRequestDto;
import com.example.demo.auth.domain.Member;
import com.example.demo.auth.domain.type.MemberStatus;
import com.example.demo.auth.mapper.MemberDtoMapper;
import com.example.demo.auth.repository.MemberRepository;
import com.example.demo.auth.service.usecase.SignUpUseCase;
import com.example.demo.auth.service.usecase.ResetPasswordUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.auth.exception.MemberErrorCode.USERNAME_ALREADY_EXISTS;
import static com.example.common.exception.util.Preconditions.validate;

@Service
@RequiredArgsConstructor
public final class MemberCommandService implements SignUpUseCase, ResetPasswordUseCase {
    private final MemberRepository memberRepository;
    private final MemberDtoMapper memberDtoMapper;

    @Override
    public Member signUp(Member member) {
        validate(!memberRepository.existsByUsername(member.username), USERNAME_ALREADY_EXISTS);
        return memberRepository.save(member);
    }

    @Override
    public Member signUp(MemberSignUpRequestDto dto, MemberStatus status) {
        return signUp(memberDtoMapper.toEntity(dto, status));
    }
}
