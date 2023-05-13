package com.example.demo.members.service;

import com.example.demo.members.domain.Member;
import com.example.demo.members.repository.MemberRepository;
import com.example.demo.members.service.usecase.MemberSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class MemberQueryService implements MemberSearchUseCase {
    private final MemberRepository memberRepository;

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }
}
