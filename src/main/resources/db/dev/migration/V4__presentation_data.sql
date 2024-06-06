-------------   ALL USERS PASSWORD is - "password" -------------------

INSERT INTO users (id, username, password, role, is_account_non_expired, is_account_non_locked,
                   is_credentials_non_expired, is_enabled)
VALUES (1, 'john_doe', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true);

INSERT INTO urls (id,short_url, long_url, user_id, visited, created_at, expired_at)
VALUES (1,'abc123', 'https://google.com', 1, 100, CURRENT_TIMESTAMP, (SELECT DATEADD('DAY', 14, CURRENT_TIMESTAMP))),
       (2,'def456', 'https://www.baeldung.com/', 1, 50, CURRENT_TIMESTAMP,
        (SELECT DATEADD('DAY', 14, CURRENT_TIMESTAMP))),
       (3,'ghi789', 'https://google.com', 1, 200, CURRENT_TIMESTAMP, (SELECT DATEADD('DAY', 14, CURRENT_TIMESTAMP))),
       (4,'jkl012', 'https://google.com', 1, 80, CURRENT_TIMESTAMP, (SELECT DATEADD('DAY', 14, CURRENT_TIMESTAMP))),
       (5,'mno345', 'https://google.com', 1, 150, CURRENT_TIMESTAMP, (SELECT DATEADD('DAY', 14, CURRENT_TIMESTAMP))),
       (6,'pqr678', 'https://google.com', 1, 120, CURRENT_TIMESTAMP, (SELECT DATEADD('DAY', 14, CURRENT_TIMESTAMP))),
       (7,'stu901', 'https://google.com', 1, 90, CURRENT_TIMESTAMP, (SELECT DATEADD('DAY', 14, CURRENT_TIMESTAMP))),
       (8,'vwx234', 'https://google.com', 1, 110, CURRENT_TIMESTAMP, (SELECT DATEADD('DAY', 14, CURRENT_TIMESTAMP))),
       (9,'yzu567', 'https://google.com', 1, 70, CURRENT_TIMESTAMP, (SELECT DATEADD('DAY', 14, CURRENT_TIMESTAMP))),
       (10,'123abc', 'https://google.com', 1, 180, CURRENT_TIMESTAMP, (SELECT DATEADD('DAY', 14, CURRENT_TIMESTAMP)));