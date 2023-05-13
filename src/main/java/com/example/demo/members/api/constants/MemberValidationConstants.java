package com.example.demo.members.api.constants;

// Compile Time Constants - Annotation Processor 적용 가능
public final class MemberValidationConstants {
    public static final class UsernameValidation {
        public static final String PATTERN = "^[a-zA-Z][a-zA-Z0-9]{2,30}$";
        public static final String MESSAGE = "계정은 영문으로 시작하여야 하며, 영문, 숫자를 포함하여 최소 3글자 이상 작성하시기 바랍니다.";

        private UsernameValidation() {}
    }

    public static final class NameValidation {
        public static final String PATTERN = "^[가-힣]{2,50}$";
        public static final String MESSAGE ="이름은 완성된 한글만 허용하며, 2글자 이상 입력하시기 바랍니다.";

        private NameValidation() {}
    }

    private MemberValidationConstants() {}
}