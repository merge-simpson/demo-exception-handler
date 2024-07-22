package com.example.demo.auth.api;

import com.example.demo.auth.api.dto.MemberCommandDto.MemberSignUpRequestDto;
import com.example.demo.auth.domain.type.MemberStatus;
import com.example.demo.auth.service.usecase.SignUpUseCase;
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
    private final SignUpUseCase signUpUseCase;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid MemberSignUpRequestDto body) {
        signUpUseCase.signUp(body, MemberStatus.ACTIVE);
    }
}
