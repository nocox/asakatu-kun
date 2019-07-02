DROP TABLE IF EXISTS user_event_association;
DROP TABLE IF EXISTS user_group_association;
DROP TABLE IF EXISTS user_status;
DROP TABLE IF EXISTS user_group;
DROP TABLE IF EXISTS user_status_master;
DROP TABLE IF EXISTS asakatu_event;
DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user(
    user_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(50) NOT NULL,
    display_name VARCHAR(50) NOT NULL,
    image_path VARCHAR(255),
    created_event BOOLEAN,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

/* TODO asakatu_eventとasakatu_event_idの名称を変更する jojo */
CREATE TABLE IF NOT EXISTS asakatu_event (
     asakatu_event_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
     start_date DATETIME,
     duration DOUBLE,
     address VARCHAR(255),
     seat_info VARCHAR(255),
     event_status ENUM('yet', 'fin', 'canceled', 'progress'),
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_event_association (
    user_id BIGINT(20),
    asakatu_event_id BIGINT(20),
    event_canceled BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (asakatu_event_id) REFERENCES asakatu_event(asakatu_event_id)
);

CREATE TABLE IF NOT EXISTS user_status_master (
    user_status_master_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    user_status_content VARCHAR(50),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_status (
    user_status_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    user_status_master_id BIGINT(20),
    user_status_comment VARCHAR(255),
    user_id BIGINT(20),
    asakatu_event_id BIGINT(20),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (asakatu_event_id) REFERENCES asakatu_event(asakatu_event_id),
    FOREIGN KEY (user_status_master_id) REFERENCES user_status_master(user_status_master_id)
);

CREATE TABLE IF NOT EXISTS user_group (
    user_group_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    order_user_id BIGINT(20),
    product_key VARCHAR(255),
    description VARCHAR(255),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (order_user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS user_group_association (
    user_id BIGINT(20),
    user_group_id BIGINT(20),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (user_group_id) REFERENCES user_group(user_group_id)
);