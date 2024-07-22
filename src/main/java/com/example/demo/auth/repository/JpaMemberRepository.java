package com.example.demo.auth.repository;

import com.example.demo.auth.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// 예제 아키텍처상 아직 같은 패키지에 관리하지만,
//   Member Repository -> 인프라스트럭처에 관심을 끄고 그저 도메인인 멤버를 취급하는 레포지터리.
//   Jpa Member Repository -> 인프라스트럭처에 대해 아는 레포지터리.
public interface JpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // NOTE: Append methods to `MemberRepository.java`
}
