--アドミン権限
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('y.mitsu92@gmail.com','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '三橋裕太','1992-09-02',26,true,'ROLE_ADMIN');

--一般権限
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('sample@xxx.co.jp','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa','テストユーザー','1990-10-01',28,false,'ROLE_GENERAL');

--記事サンプル
INSERT INTO article_list (article_id, title, memo, category, post_date)
VALUES('Article:1','Sample Title', 'Sample Memo', 'Sample Category', '2019-02-16');
