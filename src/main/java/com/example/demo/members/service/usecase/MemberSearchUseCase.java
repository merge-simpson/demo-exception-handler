package com.example.demo.members.service.usecase;

import com.example.demo.members.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberSearchUseCase {
    Page<Member> findAll(Pageable pageable);
}
