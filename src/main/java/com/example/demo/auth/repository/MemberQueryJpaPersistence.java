package com.example.demo.auth.repository;

import com.example.demo.auth.domain.Member;
import com.example.demo.auth.mapper.MemberJpaMapper;
import com.example.demo.auth.readmodel.MemberReadModel.MemberDetailsReadModel;
import com.example.demo.auth.readmodel.MemberReadModel.MemberSummaryReadModel;
import com.example.demo.auth.readmodel.MemberReadModel.SigningReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberQueryJpaPersistence implements MemberQueryRepository {

    private final MemberJpaRepository repository;
    private final MemberJpaMapper mapper;

    @Override
    public Optional<SigningReadModel> findSigningByUsername(String username) {
        return repository.findSigningByUsername(username)
                .map(mapper::toReadModel);
    }

    @Override
    public Optional<MemberDetailsReadModel> findDetailedViewByUsername(String username) {
        return repository.findDetailsByUsername(username)
                .map(mapper::toReadModel);
    }

    @Override
    public List<MemberSummaryReadModel> findListViewBy(Pageable pageable) {
        return repository.findListViewBy(pageable)
                .stream()
                .map(mapper::toReadModel)
                .toList();
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<MemberSummaryReadModel> findListViewPagedBy(Pageable pageable) {
        return repository.findSummaryPageBy(pageable)
                .map(mapper::toReadModel);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
