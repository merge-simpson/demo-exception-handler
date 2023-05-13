🌏 **다국어** 🌏
[**English**](https://github.com/merge-simpson/demo-exception-handler/blob/main/README.md) |
한국어

---

# 스펙

- 신택스 수준: 자바 17 ⏤ `설치 필요`
- 스프링 부트 3
- 도커 컴포즈 ⏤ `설치 필요`

# 구현

- `Custom Exception`은 우리가 핸들링 할 대부분의 예외의 부모 타입으로 사용됩니다. 이로써 일반화가 쉬워집니다.
- `Error Code`는 상위 인터페이스로, 우리가 사용할 열거형(enum) 커스텀 에러 코드들을 확장할 때 사용합니다.
- `Global Exception Handler`를 통해 정형적인 에러 양식을 제공하도록 일반화합니다.
- 이렇게만 해 두면 더 이상 `Global Exception Handler`에 새 메서드를 추가하지 않아도 됩니다. 기본 양식을 사용하려고 한다면요.
- 이제 새로운 예외 응답을 추가하려면, 열거형 에러 코드에 새로운 상태를 추가하기만 하면 됩니다.

그것만 하면 관리가 완료됩니다.
팀원들에게 열거형 에러 코드를 추가하도록만 하십시오. 그러면 앞으로 글로벌 익셉션 핸들러가 알아서 그것을 처리할 것입니다.

---

# 환경

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
