package com.example.demo.members.api;

import com.example.demo.members.api.dto.MemberCommandDto.MemberSignUpRequestDto;
import com.example.demo.members.api.dto.MemberCommandDto.MemberSignUpResponseDto;
import com.example.demo.members.domain.type.MemberStatus;
import com.example.demo.members.service.usecase.MemberRegistryUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberCommandApi {
    private final MemberRegistryUseCase memberRegistryUseCase;

    @PostMapping("/sign-up")
    public MemberSignUpResponseDto signUp(@RequestBody @Valid MemberSignUpRequestDto body) {
        memberRegistryUseCase.signUp(body, MemberStatus.ACTIVE);

        return MemberSignUpResponseDto.builder()
                .success(true)
                .build();
    }
}
