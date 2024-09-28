package com.example.demo.auth.service;

import com.example.demo.auth.domain.Member;
import com.example.demo.auth.readmodel.MemberReadModel.MemberSummaryReadModel;
import com.example.demo.auth.repository.MemberQueryRepository;
import com.example.demo.auth.service.usecase.MemberSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class MemberQueryService implements MemberSearchUseCase {
    private final MemberQueryRepository memberQueryRepository;

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return memberQueryRepository.findAll(pageable);
    }

    @Override
    public Page<MemberSummaryReadModel> findListViewPagedBy(Pageable pageable) {
        return memberQueryRepository.findListViewPagedBy(pageable);
    }
}
