package com.example.demo.members.api.dto;

import com.example.demo.members.api.constants.MemberValidationConstants.NameValidation;
import com.example.demo.members.api.constants.MemberValidationConstants.UsernameValidation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

public record MemberCommandDto() {
    public record MemberSignUpRequestDto(
            @Pattern(
                    regexp = UsernameValidation.PATTERN,
                    message = UsernameValidation.MESSAGE
            )
            String username,

            @Pattern(
                    regexp = NameValidation.PATTERN,
                    message = NameValidation.MESSAGE
            )
            String name,

            @Min(value = 0, message = "나이를 올바르게 입력해 주세요.")
            Integer age
    ) {}
}
