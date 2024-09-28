package com.example.demo.auth.domain.validation;

// Compile Time Constants -> Annotation 속성에 사용할 수 있도록.
public final class MemberValidationConstants {
    private MemberValidationConstants() {}

    public static final class MemberValidation {
        public static final String USERNAME_VALIDATION = "^[A-Za-z\\d]{3,30}$";
        public static final String PASSWORD_VALIDATION = "^[A-Za-z\\d~!@#$%^&*?_=\\-+,./:;]+$";
        public static final int PASSWORD_MIN_SIZE = 8;
        public static final int PASSWORD_MAX_SIZE = 100;
        public static final String FULL_NAME_VALIDATION = "^[가-힣a-zA-Z ]+$";
        public static final int FULL_NAME_MIN_SIZE = 2;
        public static final int FULL_NAME_MAX_SIZE = 30;
        private MemberValidation() {}
    }

    public static final class MemberValidationMessage {
        public static final String USERNAME_MESSAGE =
                "계정은 영문으로 시작하여, 영문, 숫자 조합으로 최소 3 글자 이상 30 글자 이하로 작성하십시오.";

        public static final String PASSWORD_MESSAGE =
                "비밀번호는 영문, 숫자, 특수문자만 사용할 수 있습니다.";
        public static final String PASSWORD_MIN_SIZE_MESSAGE = "비밀번호는 8글자 이상 입력하여야 합니다.";
        public static final String PASSWORD_MAX_SIZE_MESSAGE = "비밀번호는 최대 100글자입니다.";

        public static final String FULL_NAME_MESSAGE =
                "이름은 완성된 한글과 영문을 사용하여, 2 글자 이상 30 글자 이하로 입력하십시오.";

        // add detail message here

        private MemberValidationMessage() {}
    }
}