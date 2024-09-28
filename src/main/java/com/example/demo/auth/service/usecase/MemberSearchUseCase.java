package com.example.demo.auth.service.usecase;

import com.example.demo.auth.domain.Member;
import com.example.demo.auth.readmodel.MemberReadModel.MemberSummaryReadModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberSearchUseCase {
    Page<Member> findAll(Pageable pageable);
    Page<MemberSummaryReadModel> findListViewPagedBy(Pageable pageable);
}
