--アドミン権限
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('y.mitsu92@gmail.com','P@ssw0rd', '三橋裕太','1992-09-02',26,true,'ROLE_ADMIN');

--一般権限
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('sample@xxx.co.jp','password','テストユーザー','1990-10-01',28,false,'ROLE_GENERAL');
