🌏 **다국어** 🌏
[**English**](https://github.com/merge-simpson/demo-exception-handler/blob/main/README.md) |
한국어

# 스펙

- 신택스 수준: 자바 17 ⏤ `설치 필요`
- 스프링 부트 3
- 도커 컴포즈 ⏤ `설치 필요`
- Flyway: 로컬 DB에 스키마 구성. 서버 애플리케이션 부트 때 테이블 자동 생성.
- MapStruct: DTO, JPA Entity, 도메인 모델 등을 서로 변환.

# 구현

|       Class/Interface        | Description                                                                                                                                                                 |
|:----------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|        (I) Error Code        | 인터페이스로 작성된 이 에러 코드는, 열거형으로 작성된 다른 에러 코드의 상위 타입 역할을 합니다.                                                                                                                     |
|     (C) Custom Exception     | 모든 커스텀 예외들의 상위 타입 역할을 합니다. 각각의 커스텀 예외들이 이 예외를 상속받지 않고 바로 RuntimeException을 상속받아도 되겠지만, 그러면 Global Exception Handler에서 코드 하나로 모든 커스텀 예외에 대해 일괄적인 처리를 간단히 지원하는 방식이 적용되지 않습니다. |
| (C) Global Exception Handler | 커스텀 예외가 발생할 때 사전에 따로 처리되지 않은 경우 이곳으로 점프하도록 구현합니다. 그러면 예외 상황에 대한 응답을 줄 수 있습니다.                                                                                               |

- 이렇게만 해 두면 더 이상 `Global Exception Handler`에 새 메서드를 추가하지 않아도 됩니다.
  - 기본 예외 응답 양식을 사용해 응답합니다.
  - `CustomException`에는 `ErrorCode`가 담깁니다. 기본생성자를 사용한 경우 DEFAULT 에러 코드가 담깁니다.
  - `ErrorCode`에 있는 `name()`, Default Message, Default HTTP Status 등을 통해서 양식을 완성합니다.
- 이제 새로운 예외 응답을 추가하려면, 열거형 에러 코드에 새로운 상태를 추가하기만 하면 됩니다.

```java
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {
    USERNAME_ALREADY_EXISTS(
            "이미 사용 중인 계정입니다.", HttpStatus.CONFLICT),
    SIGN_UP_FAILED_DEFAULT(
            "회원 가입을 다시 진행해 주십시오. 오류가 지속되는 경우 문의하시기 바랍니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    MEMBER_NOT_FOUND(
            "회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DEFAULT(
            "회원 취급 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String MESSAGE;
    private final HttpStatus STATUS;
    
    @Override
    public HttpStatus defaultHttpStatus() {
        return STATUS;
    }

    @Override
    public String defaultMessage() {
        return MESSAGE;
    }

    @Override
    public MemberException defaultException() {
        return new MemberException(this);
    }

    @Override
    public MemberException defaultException(Throwable cause) {
        return new MemberException(this, cause);
    }
}
```

팀원들에게 평소에는 열거형 에러 코드에 항목만 추가하도록 안내하십시오.
그러면 앞으로 글로벌 익셉션 핸들러가 알아서 상태 코드를 포함해 메시지와 예외의 이름, 상태의 이름 등을 처리할 것입니다.

원하는 단위에서 새로운 열거형 에러 코드 클래스를 생성하는 경우, 위 구현을 참고해 간단한 준비만 갖추십시오.

---

# Demo 실행 환경

## 도커 컴포즈로 간단하게 모든 환경을 구축할 수 있도록 준비했습니다.

익숙하지 않을 뿐 굉장히 쉬우니 [설치](https://www.docker.com/products/docker-desktop/)해서 실행해 두세요.

명령어 한 줄로 모든 환경을 준비할 수 있다는 말이기도 합니다.

프로젝트 루트 경로에서 다음 명령어를 실행하시면 됩니다. 프로젝트 루트에서 실행하라는 말을 모르겠다면, `cd 명령어`를 키워드로 검색해 보세요.

```shell
docker-compose up -d --build
```

만약 작동하지 않는다면, 도커 데스크톱이 실행되고 있는지 다시 확인하고, 터미널을 다시 여세요.
터미널은 자신이 열린 채로 다른 수단으로 설치된 프로그램을 인식하지 못할 수 있습니다.

### 다시 시도해야 할 때

만약 실수한 부분이 있거나 `docker-compose.yml` 파일을 수정하는 등 이유로 다시 게시하려면 다음 명령어를 참고하십시오.

```shell
docker-compose down -v
docker-compose up -d --build
```

## 데이터베이스 접속

Docker Compose로 제공하는 로컬 DB 접속 정보

|   Host    | Port |    Database     | Username | Password |
|:---------:|:----:|:---------------:|:--------:|:--------:|
| localhost | 3306 | demo_db_mysql8  |   root   |   root   |
