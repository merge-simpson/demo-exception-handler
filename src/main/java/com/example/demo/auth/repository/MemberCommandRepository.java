package com.example.demo.auth.repository;

import com.example.demo.auth.domain.Member;

public interface MemberCommandRepository {
    Member save(Member member);
    boolean existsByUsername(String username);
}
