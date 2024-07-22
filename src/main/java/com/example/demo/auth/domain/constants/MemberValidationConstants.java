package com.example.demo.auth.domain.constants;

// Compile Time Constants - Annotation Processor 적용 가능
public final class MemberValidationConstants {
    private MemberValidationConstants() {}

    public static final class MemberValidation {
        public static final String USERNAME = "^[a-zA-Z][a-zA-Z0-9]{2,30}$";
        public static final String PASSWORD = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,100}$";
        public static final String FULL_NAME = "^[가-힣a-zA-Z ]{2,15}$";
        private MemberValidation() {}
    }

    public static final class MemberValidationMessage {
        public static final String USERNAME_MESSAGE =
                "계정은 영문으로 시작하여, 영문, 숫자 조합으로 최소 3 글자 이상 30 글자 이하로 작성하십시오.";
        public static final String PASSWORD_MESSAGE =
                "비밀번호는 영문, 숫자, 특수문자를 모두 사용하여 8 글자 이상 100 글자 이하로 입력하십시오.";
        public static final String FULL_NAME_MESSAGE =
                "이름은 완성된 한글과 영문을 사용하여, 2 글자 이상 15 글자 이하로 입력하십시오.";

        private MemberValidationMessage() {}
    }
}