CREATE TABLE IF NOT EXISTS member (
    id          BINARY(16)      PRIMARY KEY,
    username    VARCHAR(255)    NOT NULL                                    COMMENT '계정',
    password    VARCHAR(255)                                                COMMENT '비밀번호',
    full_name   VARCHAR(255)                                                COMMENT '본명(full name)',
    status      VARCHAR(30)     DEFAULT 'PENDING',
    created_at  DATETIME(6)     NOT NULL                DEFAULT CURRENT_TIMESTAMP(6),
    updated_at  DATETIME(6),

    CONSTRAINT uq_member_username UNIQUE(username)
);