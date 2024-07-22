package com.example.demo.members.exception;

import com.example.common.exception.support.CustomException;
import com.example.common.exception.support.ErrorCode;

public class MemberException extends CustomException {
    // 귀찮으면 이런 하위 예외 타입 안 만들고, enum 에러 코드만 새로 생성해서 defaultException 작성 시 바로 new CustomException 써도 됨.
    // 근데 그래도 이거 별로 안 귀찮으니까 그냥 단축키 눌러서 만드는 건 어떨지.
    // 단축키 모르면(CustomException 상속받은 상태에서):
    //  여기 에디터 배경 [오른클릭 > Generate > Override Methods]
    // 맥 인텔리제이 기본 shortcut: 컨트롤 + O 눌러서 상속받은 생성자 모두 생성할 수 있음.
    public MemberException() {
        super();
    }

    public MemberException(String message) {
        super(message);
    }

    public MemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MemberException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
