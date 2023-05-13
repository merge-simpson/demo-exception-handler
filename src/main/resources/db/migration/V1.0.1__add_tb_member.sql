CREATE TABLE IF NOT EXISTS member (
    id              BIGINT              AUTO_INCREMENT              PRIMARY KEY,
    username        VARCHAR(255)                                                        COMMENT '계정',
    name            VARCHAR(255)                                                        COMMENT '본명(Full-name)',
    age             INT                                                                 COMMENT '연령',
    status          VARCHAR(30)         DEFAULT 'PENDING',
    join_date       DATETIME(6)         NOT NULL                    DEFAULT CURRENT_TIMESTAMP(6),

    CONSTRAINT      uq_member_username  UNIQUE (username)
);