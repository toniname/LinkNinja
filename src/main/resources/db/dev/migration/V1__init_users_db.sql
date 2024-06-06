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