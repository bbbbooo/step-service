INSERT INTO user (uid, created_date, email, nickname, profile_image, provider, role)
VALUES ('dummy_uid', NOW(), 'dummy_email@example.com', 'dummy_nickname', 'http://example.com/dummy_profile.jpg', 'KAKAO', 'USER');

INSERT INTO token (access_token, user_id)
VALUES ('eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IlJPTEVfVVNFUiIsImlhdCI6MTY5NDUxNjY5MywiZXhwIjoxNjk0NTE4NDkzfQ.1fAxzcFVPQ2LBZwxG9kYYcSecxIaNh_hb1hjGhX2LhU', 1);