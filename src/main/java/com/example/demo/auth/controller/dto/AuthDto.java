package com.example.demo.auth.controller.dto;

import com.example.common.exception.validator.StrippedSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import static com.example.demo.auth.domain.validation.MemberValidationConstants.MemberValidation.FULL_NAME_MAX_SIZE;
import static com.example.demo.auth.domain.validation.MemberValidationConstants.MemberValidation.FULL_NAME_MIN_SIZE;
import static com.example.demo.auth.domain.validation.MemberValidationConstants.MemberValidation.FULL_NAME_VALIDATION;
import static com.example.demo.auth.domain.validation.MemberValidationConstants.MemberValidation.PASSWORD_VALIDATION;
import static com.example.demo.auth.domain.validation.MemberValidationConstants.MemberValidation.USERNAME_VALIDATION;
import static com.example.demo.auth.domain.validation.MemberValidationConstants.MemberValidationMessage.FULL_NAME_MESSAGE;
import static com.example.demo.auth.domain.validation.MemberValidationConstants.MemberValidationMessage.PASSWORD_MAX_SIZE_MESSAGE;
import static com.example.demo.auth.domain.validation.MemberValidationConstants.MemberValidationMessage.PASSWORD_MESSAGE;
import static com.example.demo.auth.domain.validation.MemberValidationConstants.MemberValidationMessage.PASSWORD_MIN_SIZE_MESSAGE;
import static com.example.demo.auth.domain.validation.MemberValidationConstants.MemberValidationMessage.USERNAME_MESSAGE;

public record AuthDto() {
    @Builder
    public record MemberSignUpRequestDto(
            @NotBlank(message = USERNAME_MESSAGE)
            @Pattern(
                    regexp = USERNAME_VALIDATION,
                    message = USERNAME_MESSAGE
            )
            String username,

            @NotBlank(message = PASSWORD_MESSAGE)
            @Pattern(
                    regexp = PASSWORD_VALIDATION,
                    message = PASSWORD_MESSAGE
            )
            @Size(min = 8, message = PASSWORD_MIN_SIZE_MESSAGE)
            @Size(max = 100, message = PASSWORD_MAX_SIZE_MESSAGE)
            String password,

            @NotBlank(message = FULL_NAME_MESSAGE)
            @Pattern(
                    regexp = FULL_NAME_VALIDATION,
                    message = FULL_NAME_MESSAGE
            )
            @StrippedSize(min = FULL_NAME_MIN_SIZE, message = FULL_NAME_MESSAGE)
            @StrippedSize(max = FULL_NAME_MAX_SIZE, message = FULL_NAME_MESSAGE)
            String fullName
    ) {
        public MemberSignUpRequestDto {
            fullName = fullName.strip();
        }
    }
}
