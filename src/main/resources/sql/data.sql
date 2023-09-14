INSERT INTO user (uid, created_date, email, nickname, profile_image, provider, role)
VALUES
    ('dummy_user1@example.com', '2023-09-12 10:00:00.000000', 'dummy_user1@example.com', 'KakaoUser1', 'profile1.jpg', 'KAKAO', 'USER');

INSERT INTO token (access_token, user_id)
VALUES
    ('eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE2OTQ1ODAxODMsImV4cCI6MTY5NDU5MDE4M30.Q0Z0pvTT-G9ivPeODQeBCvE7psIM7yyMN08_DLY1cNY', 1);
