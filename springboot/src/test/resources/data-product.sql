-- TODO 2019/07/06 Controllerテストのために作成、後で消す？ jojo

INSERT INTO user(username, email, password, display_name, image_path, created_event)
VALUES ('kishiiii', 'kishida@bizreach.co.jp', '$2a$10$W8UjL5w3LzP6yN1h/SM3qOjekYSjWJ8FsGhDaeBIx4/gGzTrdKwCm', 'きしー', 'https://user-images.githubusercontent.com/18584908/69044408-36ad6100-0a38-11ea-9e36-604b6c344b2a.jpeg', FALSE);
INSERT INTO user(username, email, password, display_name, image_path, created_event)
VALUES ('doiiii', 'doi@bizreach.co.jp', '$2a$10$LbFHB5TDQNNgQwk1paCzZe0AaKUnLKHxCnJzR1RgcCBfGgIHDXUkW', 'ツッチー', 'https://user-images.githubusercontent.com/18584908/69044436-47f66d80-0a38-11ea-91be-e0d5c4565ac5.jpeg', FALSE);
INSERT INTO user(username, email, password, display_name, image_path, created_event)
VALUES ('koma', 'komatsu@bizreach.co.jp', '$2a$10$uf68qeoq2jfM/NG8nyvx0.PZbt8ZLXLKJuyYUxl7hQRR2EpXxDvVy', 'こまっち', 'https://user-images.githubusercontent.com/18584908/69044459-52186c00-0a38-11ea-9dec-d51a4e429137.jpeg', FALSE);
INSERT INTO user(username, email, password, display_name, image_path, created_event)
VALUES ('den', 'dendo@bizreach.co.jp', '$2a$10$/HIeHqT1KeKStPs4bqz.Ge3IiNF8TVaBtZbUP1qJHYKBMqqPpFU3m', 'でん', 'https://user-images.githubusercontent.com/18584908/69044486-593f7a00-0a38-11ea-97ee-493939615259.jpeg', FALSE);
INSERT INTO user(username, email, password, display_name, image_path, created_event)
VALUES ('humihumi', 'ito@bizreach.co.jp', '$2a$10$pPFilOCPLuMPSMZ4PrTGoutQs9dSONS2X2wRl4S3Q7I8MnYm9tItK', 'ふみき', 'https://user-images.githubusercontent.com/18584908/69044500-5fcdf180-0a38-11ea-85bf-b87f3705f231.jpeg', FALSE);

INSERT INTO event(event_title, event_detail, start_date, duration, address, store_name, seat_info, event_status)
VALUES ('第1回19新卒朝活', '第1回19新卒朝活詳細です！', '2019-07-01 00:00:00', 3.0, '東京都渋谷区1-2-3', '朝活カフェ1', '入り口の近く', 'progress');
INSERT INTO event(event_title, event_detail, start_date, duration, address, store_name, seat_info, event_status)
VALUES ('第2回19新卒朝活', '短い詳細', '2019-07-02 00:00:00', 2.0, '東京都渋谷区2-2-3', '朝活カフェ2', '入って左', 'progress');
INSERT INTO event(event_title, event_detail, start_date, duration, address, store_name, seat_info, event_status)
VALUES ('第3回19新卒朝活', '長い詳細でええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええす', '2019-07-03 00:00:00', 3.5, '東京都渋谷区3-2-3', '長い店名でええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええす', '奥のソファー席', 'canceled');
INSERT INTO event(event_title, event_detail, start_date, duration, address, store_name, seat_info, event_status)
VALUES ('第4回19新卒朝活', '100文字くらいの詳細でええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええええす', '2019-07-04 00:00:00', 4.0, '東京都渋谷区4-2-3', '朝活カフェ4', 'テラス席', 'yet');
INSERT INTO event(event_title, event_detail, start_date, duration, address, store_name, seat_info, event_status)
VALUES ('第5回19新卒朝活', '第5回19新卒朝活詳細です！', '2019-07-05 00:00:00', 1.5, '東京都渋谷区5-2-3', '朝活カフェ5', '鏡の前の席', 'fin');

INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (1, 1, FALSE);
INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (2, 1, FALSE);
INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (3, 1, FALSE);
INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (4, 1, FALSE);
INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (4, 2, FALSE);
INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (4, 3, FALSE);
INSERT INTO user_event_association(user_id, event_id, event_canceled) VALUES (4, 4, FALSE);

INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (1, 'レッツ朝活！！！', 1, 1);
INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (1, 'わいわい！！！', 1, 2);
INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (2, '遅れそう', 2, 1);
INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (1, '今日も頑張ろう', 3, 1);
INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (1, 'わーい', 4, 1);
INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (1, 'やるぞ', 4, 2);
INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (1, '眠い', 4, 3);
INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (2, 'ごめんさい', 4, 4);
INSERT INTO user_status(user_status_master_id, user_status_comment, user_id, event_id) VALUES (1, 'ASAKATSU', 4, 5);

INSERT INTO user_group(order_user_id, product_key, description) VALUES (1, '3xq239r7h493c', '朝活グループ');
INSERT INTO user_group(order_user_id, product_key, description) VALUES (2, 'f234jfqp4243jpr2', '朝活グループ2');
INSERT INTO user_group(order_user_id, product_key, description) VALUES (3, 'f32r3fk32rfekpw', '朝活グループ3');
INSERT INTO user_group(order_user_id, product_key, description) VALUES (4, '234ere3e433', '朝活グループ4');

INSERT INTO user_group_association(user_id, user_group_id) VALUES (1, 1);
INSERT INTO user_group_association(user_id, user_group_id) VALUES (1, 2);
INSERT INTO user_group_association(user_id, user_group_id) VALUES (1, 3);
INSERT INTO user_group_association(user_id, user_group_id) VALUES (1, 4);
INSERT INTO user_group_association(user_id, user_group_id) VALUES (2, 4);
INSERT INTO user_group_association(user_id, user_group_id) VALUES (3, 4);
INSERT INTO user_group_association(user_id, user_group_id) VALUES (4, 4);
