package com.example.demo.auth.controller;

import com.example.demo.auth.controller.dto.AuthDto.MemberSignUpRequestDto;
import com.example.demo.auth.domain.data.MemberStatus;
import com.example.demo.auth.service.usecase.SignUpUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class AuthApi {
    private final SignUpUseCase signUpUseCase;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid MemberSignUpRequestDto body) {
        Instant now = Instant.now();
        signUpUseCase.signUp(body, MemberStatus.ACTIVE, now);
    }
}
