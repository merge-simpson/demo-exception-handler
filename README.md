🌏 **Multilingual** 🌏
English |
[**한국어**](https://github.com/merge-simpson/demo-exception-handler/blob/main/README.kr.md)

# Spec Overview

- Syntax Level: Java 17(JDK 17) ⏤ `required`
- Spring Boot 3
- Docker Compose ⏤ `required`

# Implementation Overview

- `Custom Exception` is a super class of every exception class we handle.
- `Error Code` is an interface to expand enumerated `Custom Error Codes`(enum class).
- `Global Exception Handler` will provide specified error format.
- You don't have to append any method to `Global Exception Handler`, if you will use the base format.
- From now on, when appending one more exceptional response, just append one more state to enumerated error code.

That's going to be all.

Let your team append enumerated `Error Code`, then it will be caught by `Global Exception Handler`.

---

# Environments

## Docker Compose Will Prepare All Environments You Need

The easiest way is downloading and running [docker desktop](https://www.docker.com/products/docker-desktop/).

It means you can prepare all environments using only one line of command with `docker-compose`.

Run this command at the root of project. If you don't know, search `change directory command`.

```shell
docker-compose up -d --build
```

If it doesn't work, check one more time, if docker desktop is opened. And reopen your terminal.

Terminal can miss some programs installed via another way, when it was open.

### You Have to Retry?

If you took any mistake or edited the `docker-compose.yml` file, you can re-upload.

```shell
docker-compose down -v
docker-compose up -d --build
```

## Access Database

Local DB is already installed by `docker-compose`.

You can directly access the database with this:

|   Host    | Port |    Database     | Username | Password |
|:---------:|:----:|:---------------:|:--------:|:--------:|
| localhost | 3306 | demo_db_mysql8  |   root   |   root   |
