package com.example.demo.auth.service;

import com.example.demo.auth.controller.dto.AuthDto.MemberSignUpRequestDto;
import com.example.demo.auth.domain.Member;
import com.example.demo.auth.domain.data.MemberStatus;
import com.example.demo.auth.mapper.MemberDtoMapper;
import com.example.demo.auth.repository.MemberCommandRepository;
import com.example.demo.auth.service.usecase.SignUpUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.example.demo.auth.exception.SignUpErrorCode.USERNAME_ALREADY_EXISTS;
import static com.example.common.util.exception.Preconditions.validate;

@Service
@RequiredArgsConstructor
public final class AuthService implements SignUpUseCase {
    private final MemberCommandRepository memberCommandRepository;
    private final MemberDtoMapper memberDtoMapper;

    @Override
    public Member signUp(Member member) {
        validate(!memberCommandRepository.existsByUsername(member.username), USERNAME_ALREADY_EXISTS);
        return memberCommandRepository.save(member);
    }

    @Override
    public Member signUp(MemberSignUpRequestDto dto, MemberStatus status, Instant createdAt) {
        Member entity = memberDtoMapper.toEntity(dto, status, createdAt);
        return signUp(entity);
    }
}
