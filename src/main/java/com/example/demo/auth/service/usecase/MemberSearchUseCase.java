package com.example.demo.auth.service.usecase;

import com.example.demo.auth.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberSearchUseCase {
    Page<Member> findAll(Pageable pageable);
}
