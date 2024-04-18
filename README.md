🌏 **Multilingual** 🌏
English |
[**한국어**](https://github.com/merge-simpson/demo-exception-handler/blob/main/README.kr.md)

# Key Points

- **I have decided to use `interface ErrorCode` instead of `enum ErrorCode`.
  This will prove to be a better decision.** 
  By doing so, we can extend the interface and classify the specific error codes as we wish.
- **The `CustomException` class is handled by the `GlobalExceptionHandler`.**
  This allows us to handle both `ErrorCode` and `CustomException`.
  No additional code is needed to handle each specific custom exception or each error code.

# Spec Overview

- Syntax Level: Java 17(JDK 17) ⏤ `required`
- Spring Boot 3
- Docker Compose ⏤ `required`
- Flyway: Constructs the schema in the local DB. Tables are created automatically when the server application boots.
- MapStruct: Transforms DTOs, JPA Entities, domain models, etc.

# Implementation Overview

|       Class/Interface        | Description                                                                                                                                                                                                      |
|:----------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|        (I) Error Code        | This interface serves as a super type for each enumerated error code class.                                                                                                                                      |
|     (C) Custom Exception     | Serves as the superclass for all custom exceptions. Even if each custom exception could inherit directly from RuntimeException, that would not allow for simple, uniform handling by the GlobalExceptionHandler. |
| (C) Global Exception Handler | Handle a custom exception when it jumps to this location, without prior specific handling. And it supplies the response to the exception situation.                                                              |

- You don't have to add any more methods to `GlobalExceptionHandler`, if you want.
    -  It uses a default exception response format.
    - `CustomException` has an `ErrorCode`.
      If instantiated with the default constructor, the `DEFAULT_ERROR_CODE` is used.
    - API Error format will be completed with `name()`, `defaultMessage()`, `defaultHttpStatus()` from `ErrorCode`.
- To add new exceptional responses, the simple rest job is just to add the enumerated item to the implemented enum error code.

That's all.

What your team should remember to use this is
that your `ErrorCode` and each detailed custom exception will be caught by `Global Exception Handler`.

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

The easiest way to install Docker Compose is to download and run [Docker Desktop](https://www.docker.com/products/docker-desktop/).

It means you can prepare all required local environments only with a line of command using `docker-compose`.

Run below command at the root of the project. If you don't know, search "change directory command".

And you should find the `docker-comopse.yml` when you use `dir`(window) or `ls`(mac, linux).

```shell
docker-compose up -d
```

If it doesn't work, check if Docker is running. Then reopen your terminal.

A terminal may not recognize a program which is installed while the terminal is open.

### Do You Have to Retry?

If you need to repost due to a mistake or because you've modified the `docker-compose.yml` file,
refer to the following commands.

```shell
# -v means that you will also remove the volume container including DB data.
docker-compose down -v
docker-compose up -d
```

## Access The Database

Docker Compose already installed your new local DB.

You can directly access the database with this:

|   Host    | Port |    Database     | Username | Password |
|:---------:|:----:|:---------------:|:--------:|:--------:|
| localhost | 3307 | demo_db_mysql8  |   root   |   root   |