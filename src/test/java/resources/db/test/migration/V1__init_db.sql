CREATE TABLE IF NOT EXISTS users(
                                    id BIGSERIAL PRIMARY KEY,
                                    username VARCHAR(64) NOT NULL ,
                                    password VARCHAR(512) NOT NULL ,
                                    role VARCHAR(12) NOT NULL ,
                                    is_account_non_expired BOOLEAN DEFAULT TRUE,
                                    is_account_non_locked BOOLEAN DEFAULT TRUE,
                                    is_credentials_non_expired BOOLEAN DEFAULT TRUE,
                                    is_enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS urls(
                                   id BIGSERIAL PRIMARY KEY,
                                   short_url VARCHAR(12) NOT NULL ,
                                   long_url VARCHAR(256) NOT NULL ,
                                   user_id BIGINT REFERENCES users(id),
                                   visited BIGINT DEFAULT 0,
                                   created_at TIMESTAMP DEFAULT current_timestamp,
                                   expired_at TIMESTAMP DEFAULT current_timestamp + INTERVAL '14 days'
);
CREATE SEQUENCE USERS_SEQ START WITH 20 INCREMENT BY 1;
CREATE SEQUENCE URLS_SEQ  START WITH 20 INCREMENT BY 1;