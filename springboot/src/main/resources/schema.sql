CREATE TABLE IF NOT EXISTS user(
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(50) NOT NULL,
    display_name VARCHAR(50) NOT NULL,
    image_path VARCHAR(255),
    created_event BOOLEAN, # TODO イベントテーブルに作者カラムを追加する可能性もある・後Boolでいいかも怪しい jojo
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS event (
     event_id INT PRIMARY KEY AUTO_INCREMENT,
     start_date DATETIME,
     duration DOUBLE,
     address VARCHAR(255),
     seat_info VARCHAR(255),
     event_status VARCHAR(50),
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_event_association (
    user_id INT,
    event_id INT,
    event_canceled BOOLEAN, # TODO enumでもいいかも jojo
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (event_id) REFERENCES event(event_id)
);

CREATE TABLE IF NOT EXISTS user_status_master (
    user_status_master_id INT PRIMARY KEY AUTO_INCREMENT,
    user_status_content VARCHAR(50),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_status (
    user_status_id INT PRIMARY KEY AUTO_INCREMENT,
    user_status_master_id INT,
    user_status_comment VARCHAR(255),
    user_id INT,
    event_id INT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (event_id) REFERENCES event(event_id),
    FOREIGN KEY (user_status_master_id) REFERENCES user_status_master(user_status_master_id)
);

CREATE TABLE IF NOT EXISTS user_group (
    user_group_id INT PRIMARY KEY AUTO_INCREMENT,
    order_user_id INT,
    product_key VARCHAR(255),
    description VARCHAR(255),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (order_user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS user_group_association (
    user_id INT,
    user_group_id INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (user_group_id) REFERENCES user_group(user_group_id)
);