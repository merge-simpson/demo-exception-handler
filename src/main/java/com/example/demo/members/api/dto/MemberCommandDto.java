package com.example.demo.members.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static com.example.demo.members.domain.constants.MemberValidationConstants.MemberValidation.FULL_NAME;
import static com.example.demo.members.domain.constants.MemberValidationConstants.MemberValidation.PASSWORD;
import static com.example.demo.members.domain.constants.MemberValidationConstants.MemberValidation.USERNAME;
import static com.example.demo.members.domain.constants.MemberValidationConstants.MemberValidationMessage.FULL_NAME_MESSAGE;
import static com.example.demo.members.domain.constants.MemberValidationConstants.MemberValidationMessage.PASSWORD_MESSAGE;
import static com.example.demo.members.domain.constants.MemberValidationConstants.MemberValidationMessage.USERNAME_MESSAGE;

public record MemberCommandDto() {
    public record MemberSignUpRequestDto(
            @NotBlank(message = USERNAME_MESSAGE)
            @Pattern(
                    regexp = USERNAME,
                    message = USERNAME_MESSAGE
            )
            String username,

            @NotBlank(message = PASSWORD_MESSAGE)
            @Pattern(
                    regexp = PASSWORD,
                    message = PASSWORD_MESSAGE
            )
            String password,

            @NotBlank(message = FULL_NAME_MESSAGE)
            @Pattern(
                    regexp = FULL_NAME,
                    message = FULL_NAME_MESSAGE
            )
            String fullName
    ) {
        // Compact 생성자: 생성자 파라미터를 다룰 수 있음
        public MemberSignUpRequestDto {
            // 소괄호가 없이 작성되어야 하며, 주생성자 진입 전 거치는 곳
            fullName = fullName.strip();
        }
    }
}
