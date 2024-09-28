package com.example.common.exception.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrippedSizeValidator implements ConstraintValidator<StrippedSize, String> {

    private int min;
    private int max;

    @Override
    public void initialize(StrippedSize constraintAnnotation) {
        assert max >= min : "최댓값이 최솟값보다 크거나 같아야 합니다.";
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            // null 체크는 안 함.
            return true;
        }
        int length = value.strip().length();
        return min <= length && length <= max;
    }
}
