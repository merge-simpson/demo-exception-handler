package com.example.demo.members.api;

import com.example.demo.members.api.dto.MemberCommandDto.MemberSignUpRequestDto;
import com.example.demo.members.domain.type.MemberStatus;
import com.example.demo.members.service.usecase.MemberRegistryUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberCommandApi {
    private final MemberRegistryUseCase memberRegistryUseCase;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid MemberSignUpRequestDto body) {
        memberRegistryUseCase.signUp(body, MemberStatus.ACTIVE);
    }
}
