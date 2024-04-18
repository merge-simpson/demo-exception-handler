🌏 **Multilingual** 🌏
English |
[**한국어**](https://github.com/merge-simpson/demo-exception-handler/blob/main/README.kr.md)

# What to See

- I decided to use `interface ErrorCode`, instead of `enum ErrorCode`.
  - Then we can expand it, and classify the specific error codes.
- A `CustomException` class is handled at `GlobalExceptionHandler`.
  - Then we can treat the `ErrorCode` and `CustomException`.
  - Additional methods for each detail custom exception or each error code are not required.

# Spec Overview

- Syntax Level: Java 17(JDK 17) ⏤ `required`
- Spring Boot 3
- Docker Compose ⏤ `required`
- Flyway: To construct your local db schema, when booting server application.
- MapStruct: To convert dto, jpa entity, domain models.

# Implementation Overview

|       Class/Interface        | Description                                                                                                                                                         |
|:----------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|        (I) Error Code        | This interface is a super type of each enumerated error code class.                                                                                                 |
|     (C) Custom Exception     | This is super class of every custom exception. Global Exception Handler will simply treat when your custom exception is extended with this `CustomException` class. |
| (C) Global Exception Handler | Unhandled custom exception will come here. Then your controller will response the api error format about the situation.                                             |

- `Global Exception Handler` doesn't need more method, initially.
    - Base format of exceptional response will be used.
    - `CustomException` has an `ErrorCode`. Default constructor will use DEFAULT_ERROR_CODE.
    - API Error format will be completed with `name()`, Default Message, Default HTTP Status of Error Code.
- From now on, to append more exceptional response, the rest job is just writing the "state" on the enum error code.

That's going to be all.

What your team should remember for it is, when writing "state" for exception, it will be caught by `Global Exception Handler`.

```java
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {
  USERNAME_ALREADY_EXISTS("이미 사용 중인 계정입니다.", HttpStatus.CONFLICT),
  SIGN_UP_FAILED_DEFAULT(
          "회원 가입을 다시 진행해 주십시오. 오류가 지속되는 경우 문의하시기 바랍니다.",
          HttpStatus.INTERNAL_SERVER_ERROR
  ),
  MEMBER_NOT_FOUND("회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  DEFAULT("회원 취급 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

  private final String message;
  private final HttpStatus status;

  @Override
  public String defaultMessage() {
    return message;
  }

  @Override
  public HttpStatus defaultHttpStatus() {
    return status;
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

---

# Environments for Demo

## Docker Compose Will Prepare All Environments You Need

The easiest way is downloading and running [docker desktop](https://www.docker.com/products/docker-desktop/).

It means you can prepare all environments using only one line of command with `docker-compose`.

Run this command at the root of project. If you don't know, search `change directory command`.

```shell
docker-compose up -d
```

If it doesn't work, check one more time, if docker desktop is opened. And reopen your terminal.

Terminal can miss some programs installed via another way, when it was open.

### You Have to Retry?

If you took any mistake or edited the `docker-compose.yml` file, you can re-upload.

```shell
docker-compose down -v
docker-compose up -d
```

## Access Database

Local DB is already installed by `docker-compose`.

You can directly access the database with this:

|   Host    | Port |    Database     | Username | Password |
|:---------:|:----:|:---------------:|:--------:|:--------:|
| localhost | 3307 | demo_db_mysql8  |   root   |   root   |