package com.example.demo.auth.exception;

import com.example.common.exception.support.CustomException;
import com.example.common.exception.support.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

@RequiredArgsConstructor
public enum SignUpErrorCode implements ErrorCode {
    USERNAME_ALREADY_EXISTS(
            "이미 사용 중인 계정입니다.",
            HttpStatus.CONFLICT
    ),
    DEFAULT(
            "회원 가입을 다시 진행해 주십시오. 오류가 지속되는 경우 문의하시기 바랍니다.",
            HttpStatus.INTERNAL_SERVER_ERROR
    );

    private final String message;
    private final HttpStatus status;

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus httpStatus() {
        return status;
    }

    @Override
    public SignUpException exception() {
        return new SignUpException(this);
    }

    @Override
    public SignUpException exception(Throwable cause) {
        return new SignUpException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable action) {
        return new SignUpException(this, action);
    }

    @Override
    public RuntimeException exception(Runnable action, Throwable cause) {
        return new SignUpException(this, action, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payloadSupplier) {
        return new SignUpException(this, payloadSupplier);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
        return new CustomException(this, payloadSupplier, cause);
    }


}
