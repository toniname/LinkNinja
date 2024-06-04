-------------   ALL USERS PASSWORD is - "password" -------------------

INSERT INTO users (id,username, password, role, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled)
VALUES
    (1,'john_doe', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    (2,'jane_smith', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    (3,'alex_jones', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    (4,'sara_williams', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    (5,'michael_clark', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    (6,'emily_brown', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    (7,'david_miller', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    (8,'amy_jackson', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    (9,'kevin_white', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    (10,'olivia_anderson', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true);

INSERT INTO urls (short_url, long_url, user_id, visited, created_at, expired_at)
VALUES
    ('abc123', 'https://google.com', 1, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '14 DAYS'),
    ('def456', 'https://google.com', 2, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '14 DAYS'),
    ('ghi789', 'https://google.com', 3, 200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '14 DAYS'),
    ('jkl012', 'https://google.com', 4, 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '14 DAYS'),
    ('mno345', 'https://google.com', 5, 150, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '14 DAYS'),
    ('pqr678', 'https://google.com', 6, 120, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '14 DAYS'),
    ('stu901', 'https://google.com', 7, 90, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '14 DAYS'),
    ('vwx234', 'https://google.com', 8, 110, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '14 DAYS'),
    ('yzu567', 'https://google.com', 9, 70, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP - INTERVAL '14 DAYS'),
    ('123abc', 'https://google.com', 10, 180, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP - INTERVAL '14 DAYS');
