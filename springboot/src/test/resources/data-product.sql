-- TODO 2019/07/06 Controllerテストのために作成、後で消す？ jojo

INSERT INTO user(username, email, password, display_name, image_path, created_event)
VALUES ('kishiiii', 'kishida@bizreach.co.jp', 'pass1', 'きしー', '/images/profile/2u1e0h30x38.jpg', FALSE);
INSERT INTO user(username, email, password, display_name, image_path, created_event)
VALUES ('doiiii', 'doi@bizreach.co.jp', 'pass2', 'ツッチー', '/images/profile/ewp3rf33r.jpg', FALSE);
INSERT INTO user(username, email, password, display_name, image_path, created_event)
VALUES ('koma', 'komatsu@bizreach.co.jp', 'pass3', 'こまっち', '/images/profile/fwep3r3rf.jpg', FALSE);
INSERT INTO user(username, email, password, display_name, image_path, created_event)
VALUES ('den', 'dendo@bizreach.co.jp', 'pass4', 'でん', '/images/profile/2e3r3jorqwe3.jpg', FALSE);
INSERT INTO user(username, email, password, display_name, image_path, created_event)
VALUES ('humihumi', 'ito@bizreach.co.jp', 'pass5', 'ふみき', '/images/profile/3r2jf044.jpg', FALSE);

INSERT INTO event(start_date, duration, address, seat_info, event_status)
VALUES ('2019-07-01 00:00:00', 3.0, '東京都渋谷区1-2-3', '入り口の近く', 'progress');
INSERT INTO event(start_date, duration, address, seat_info, event_status)
VALUES ('2019-07-02 00:00:00', 2.0, '東京都渋谷区2-2-3', '入って左', 'progress');
INSERT INTO event(start_date, duration, address, seat_info, event_status)
VALUES ('2019-07-03 00:00:00', 3.5, '東京都渋谷区3-2-3', '奥のソファー席', 'canceled');
INSERT INTO event(start_date, duration, address, seat_info, event_status)
VALUES ('2019-07-04 00:00:00', 4.0, '東京都渋谷区4-2-3', 'テラス席', 'yet');
INSERT INTO event(start_date, duration, address, seat_info, event_status)
VALUES ('2019-07-05 00:00:00', 1.5, '東京都渋谷区5-2-3', '鏡の前の席', 'fin');

INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (1, 1, FALSE);
-- INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (1, 1, FALSE);
INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (2, 1, FALSE);
INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (3, 1, FALSE);
-- INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (4, 1, FALSE);
-- INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (2, 1, FALSE);
INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (4, 1, FALSE);
INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (4, 2, FALSE);
INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (4, 3, FALSE);
INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (4, 4, FALSE);

INSERT INTO user_status_master(user_status_content) VALUES ('💪');
INSERT INTO user_status_master(user_status_content) VALUES ('🤔');
INSERT INTO user_status_master(user_status_content) VALUES ('😀');
INSERT INTO user_status_master(user_status_content) VALUES ('👍');

INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (1, 'レッツ朝活！！！', 1, 1);
-- INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (1, 'わいわい！！！', 1, 1);
INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (2, '遅れそう', 2, 1);
INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (1, '今日も頑張ろう', 3, 1);
-- INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (1, 'わーい', 4, 1);
INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (1, 'やるぞ', 4, 1);
INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (1, '眠い', 4, 2);
INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (2, 'ごめんさい', 4, 3);
INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (1, 'ASAKATSU', 4, 4);

INSERT INTO user_group(order_user_id, product_key, description) VALUES (1, '3xq239r7h493c', '朝活グループ');
INSERT INTO user_group(order_user_id, product_key, description) VALUES (2, 'f234jfqp4243jpr2', '朝活グループ2');
INSERT INTO user_group(order_user_id, product_key, description) VALUES (3, 'f32r3fk32rfekpw', '朝活グループ3');
INSERT INTO user_group(order_user_id, product_key, description) VALUES (4, '234ere3e433', '朝活グループ4');

INSERT INTO user_group_association(user_id, user_group_id) VALUES (1, 1);
-- INSERT INTO user_group_association(user_id, user_group_id) VALUES (1, 1);
INSERT INTO user_group_association(user_id, user_group_id) VALUES (1, 2);
INSERT INTO user_group_association(user_id, user_group_id) VALUES (1, 3);
-- INSERT INTO user_group_association(user_id, user_group_id) VALUES (1, 4);
INSERT INTO user_group_association(user_id, user_group_id) VALUES (1, 4);
INSERT INTO user_group_association(user_id, user_group_id) VALUES (2, 4);
INSERT INTO user_group_association(user_id, user_group_id) VALUES (3, 4);
INSERT INTO user_group_association(user_id, user_group_id) VALUES (4, 4);