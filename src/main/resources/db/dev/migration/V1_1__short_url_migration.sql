CREATE TABLE IF NOT EXISTS urls(
                                   id BIGINT PRIMARY KEY,
                                   short_url VARCHAR(12) UNIQUE ,
                                   long_url VARCHAR(256) NOT NULL ,
                                   user_id INTEGER REFERENCES users(id),
                                   visited BIGINT DEFAULT 0,
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   expired_at TIMESTAMP DEFAULT (SELECT DATEADD('DAY', 14, CURRENT_TIMESTAMP))
);