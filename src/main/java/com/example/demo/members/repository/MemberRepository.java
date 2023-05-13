package com.example.demo.members.repository;

import com.example.demo.members.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepository {
    Member save(Member member);
    Page<Member> findAll(Pageable pageable);
    boolean existsByUsername(String username);
}
