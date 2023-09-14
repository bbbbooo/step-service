INSERT INTO scrap (member_no, question_no)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (1, 7),
    (1, 8),
    (1, 9),
    (1, 10),
    (1, 11);


INSERT INTO work_book (member_no, question_nos, is_shared)
VALUES (1, '1, 2, 3, 4, 5', true),
       (1, '6, 7, 8, 9, 10', false);


INSERT INTO user (uid, created_date, email, nickname, profile_image, provider, role)
VALUES
    ('dummy_user1@example.com', '2023-09-12 10:00:00.000000', 'dummy_user1@example.com', 'KakaoUser1', 'profile1.jpg', 'KAKAO', 'USER');

INSERT INTO token (access_token, user_id)
VALUES
    ('eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE2OTQ1ODAxODMsImV4cCI6MTY5NDU5MDE4M30.Q0Z0pvTT-G9ivPeODQeBCvE7psIM7yyMN08_DLY1cNY', 1);


