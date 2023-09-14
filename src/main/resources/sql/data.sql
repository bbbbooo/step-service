INSERT INTO scrap (corrected_marking_status, marked_no, member_no, question_no)
VALUES
    (1, 1, 1, 1),
    (0, 2, 1, 2),
    (1, 3, 1, 3),
    (0, 4, 1, 4),
    (1, 5, 1, 5),
    (0, 6, 1, 6),
    (1, 7, 1, 7),
    (0, 8, 1, 8),
    (1, 9, 1, 9),
    (0, 10, 1, 10),
    (0, 11, 1, 11);

INSERT INTO question (question_body, question_correct_answer, question_large_classification,
                      question_middle_classification, question_small_classification,
                      question_source, question_subject, question_view_type)
VALUES
    ('문제 1 내용', 1, '대분류 1', '중분류 1', '소분류 1', '출처 1', '문제 제목 1', 1),
    ('문제 2 내용', 2, '대분류 2', '중분류 2', '소분류 2', '출처 2', '문제 제목 2', 2),
    ('문제 3 내용', 3, '대분류 3', '중분류 3', '소분류 3', '출처 3', '문제 제목 3', 3),
    ('문제 4 내용', 4, '대분류 4', '중분류 4', '소분류 4', '출처 4', '문제 제목 4', 4),
    ('문제 5 내용', 5, '대분류 5', '중분류 5', '소분류 5', '출처 5', '문제 제목 5', 5),
    ('문제 6 내용', 6, '대분류 6', '중분류 6', '소분류 6', '출처 6', '문제 제목 6', 6),
    ('문제 7 내용', 7, '대분류 7', '중분류 7', '소분류 7', '출처 7', '문제 제목 7', 7),
    ('문제 8 내용', 8, '대분류 8', '중분류 8', '소분류 8', '출처 8', '문제 제목 8', 8),
    ('문제 9 내용', 9, '대분류 9', '중분류 9', '소분류 9', '출처 9', '문제 제목 9', 9),
    ('문제 10 내용', 10, '대분류 10', '중분류 10', '소분류 10', '출처 10', '문제 제목 10', 10),
    ('문제 11 내용', 11, '대분류 11', '중분류 11', '소분류 11', '출처 11', '문제 제목 11', 11);



INSERT INTO user (uid, created_date, email, nickname, profile_image, provider, role)
VALUES
    ('dummy_user1@example.com', '2023-09-12 10:00:00.000000', 'dummy_user1@example.com', 'KakaoUser1', 'profile1.jpg', 'KAKAO', 'USER');

INSERT INTO token (access_token, user_id)
VALUES
    ('eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE2OTQ1ODAxODMsImV4cCI6MTY5NDU5MDE4M30.Q0Z0pvTT-G9ivPeODQeBCvE7psIM7yyMN08_DLY1cNY', 1);
