package com.example.demo.auth.repository;

import com.example.demo.auth.domain.Member;
import com.example.demo.auth.repository.projection.MemberProjection.MemberDetailsProjection;
import com.example.demo.auth.repository.projection.MemberProjection.MemberSummaryProjection;
import com.example.demo.auth.repository.projection.MemberProjection.SigningProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// 예제 아키텍처상 아직 같은 패키지에 관리하지만,
//   Member Repository -> 인프라스트럭처에 관심을 끄고 그저 도메인인 멤버를 취급하는 레포지터리.
//   Jpa Member Repository -> 인프라스트럭처에 대해 아는 레포지터리.
public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    // optional
    Optional<SigningProjection> findSigningByUsername(String username);
    Optional<MemberDetailsProjection> findDetailsByUsername(String username);

    // list
    List<MemberSummaryProjection> findListViewBy(Pageable pageable);

    // page
    Page<MemberSummaryProjection> findSummaryPageBy(Pageable pageable);

    boolean existsByUsername(String username);
}
