package com.example.demo.auth.repository;

import com.example.demo.auth.domain.Member;
import com.example.demo.auth.readmodel.MemberReadModel.MemberListViewReadModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepository {
    Member save(Member member);
    Page<Member> findAll(Pageable pageable);
    Page<MemberListViewReadModel> findListViewPagedBy(Pageable pageable);
    boolean existsByUsername(String username);
}
