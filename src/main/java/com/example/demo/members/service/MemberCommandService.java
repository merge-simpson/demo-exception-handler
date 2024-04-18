package com.example.demo.members.service;

import com.example.demo.members.api.dto.MemberCommandDto.MemberSignUpRequestDto;
import com.example.demo.members.domain.Member;
import com.example.demo.members.domain.type.MemberStatus;
import com.example.demo.members.mapper.MemberDtoMapper;
import com.example.demo.members.repository.MemberRepository;
import com.example.demo.members.service.usecase.MemberRegistryUseCase;
import com.example.demo.members.service.usecase.MemberPasswordResetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.members.exception.MemberErrorCode.USERNAME_ALREADY_EXISTS;
import static com.example.demo.common.support.Preconditions.validate;

@Service
@RequiredArgsConstructor
public final class MemberCommandService implements MemberRegistryUseCase, MemberPasswordResetUseCase {
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
