package com.example.demo.auth.repository;

import com.example.demo.auth.domain.Member;
import com.example.demo.auth.readmodel.MemberReadModel.MemberDetailsReadModel;
import com.example.demo.auth.readmodel.MemberReadModel.MemberSummaryReadModel;
import com.example.demo.auth.readmodel.MemberReadModel.SigningReadModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MemberQueryRepository {
    // optional
    Optional<SigningReadModel> findSigningByUsername(String username);
    Optional<MemberDetailsReadModel> findDetailedViewByUsername(String username);

    // list
    List<MemberSummaryReadModel> findListViewBy(Pageable pageable);

    // page
    Page<Member> findAll(Pageable pageable);
    Page<MemberSummaryReadModel> findListViewPagedBy(Pageable pageable);

    // exists
    boolean existsByUsername(String username);
}
