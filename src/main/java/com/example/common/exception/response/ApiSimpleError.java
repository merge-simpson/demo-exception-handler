package com.example.common.exception.response;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder
public record ApiSimpleError(@NonNull String field, @NonNull String message) {
    public static List<ApiSimpleError> listOfCauseSimpleError(Throwable cause) {
        return List.of(normalizeCause(cause));
    }

    public static ApiSimpleError[] normalizeCause(Throwable cause) {
        int depth = 0;
        ApiSimpleError[] subErrors;
        Throwable currentCause = cause.getCause();

        while (currentCause != null) {
            currentCause = currentCause.getCause();
            depth++;
        }

        subErrors = new ApiSimpleError[depth];
        currentCause = cause;
        for (int i = 0; i < depth; i++) {
            String errorFullName = currentCause.getClass().getSimpleName();
            String field = errorFullName.substring(errorFullName.lastIndexOf('.') + 1);
            subErrors[i] = ApiSimpleError.builder()
                    .field(field)
                    .message(currentCause.getLocalizedMessage())
                    .build();

            currentCause = currentCause.getCause();
        }

        return subErrors;
    }
}