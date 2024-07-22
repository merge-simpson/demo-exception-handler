package com.example.demo.auth.repository;

import com.example.demo.auth.domain.Member;
import com.example.demo.auth.mapper.MemberJpaMapper;
import com.example.demo.auth.readmodel.MemberReadModel.MemberListViewReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberJpaPersistence implements MemberRepository {

    private final MemberJpaRepository repository;
    private final MemberJpaMapper mapper;

    @Override
    public Member save(Member member) {
        return repository.save(member);
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<MemberListViewReadModel> findListViewPagedBy(Pageable pageable) {
        return repository.findListViewBy(pageable)
                .map(mapper::toReadModel);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
