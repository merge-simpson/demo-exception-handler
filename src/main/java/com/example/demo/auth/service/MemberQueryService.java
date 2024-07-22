package com.example.demo.auth.service;

import com.example.demo.auth.domain.Member;
import com.example.demo.auth.readmodel.MemberReadModel.MemberListViewReadModel;
import com.example.demo.auth.repository.MemberRepository;
import com.example.demo.auth.service.usecase.MemberSearchUseCase;
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

    @Override
    public Page<MemberListViewReadModel> findListViewPagedBy(Pageable pageable) {
        return memberRepository.findListViewPagedBy(pageable);
    }
}
