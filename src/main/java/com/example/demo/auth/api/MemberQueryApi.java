package com.example.demo.auth.api;

import com.example.demo.auth.api.dto.MemberQueryDto.MemberQueryAllResponseDto;
import com.example.demo.auth.domain.Member;
import com.example.demo.auth.service.usecase.MemberSearchUseCase;
import com.example.common.exception.status2xx.NoContentException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class MemberQueryApi {

    private final MemberSearchUseCase memberSearchUseCase;

    @GetMapping("/members")
    public MemberQueryAllResponseDto all(
            @PageableDefault(size=12, sort="joinDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        // Page 관련 전처리
        // Zero Based Paging to One Based Paging(External)
        pageable = pageable.previousOrFirst(); // 0 이하는 모두 1 페이지.

        // Page<...> 사용 시 성능 저하가 발생하므로 삼갈 것. 여기서는 예시를 위해 작성함.
        Page<Member> memberPage = memberSearchUseCase.findAll(pageable);

        if (memberPage.isEmpty()) throw new NoContentException();

        return MemberQueryAllResponseDto.builder()
                .members(memberPage.getContent())
                .lastPage(memberPage.getTotalPages())
                .total(memberPage.getTotalElements())
                .build();
    }
}
