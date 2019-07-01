DROP TABLE IF EXISTS m_emp;
CREATE TABLE IF NOT EXISTS m_emp
(
    empno        bigint(20) AUTO_INCREMENT,
    empname      varchar(255) DEFAULT NULL,
    departmentid varchar(10)  DEFAULT NULL,
    PRIMARY KEY (empno)
);

DROP TABLE IF EXISTS phone;
CREATE TABLE IF NOT EXISTS phone
(
    phone_id bigint(20) AUTO_INCREMENT,
    number   varchar(255),
    empno    bigint,
    PRIMARY KEY (phone_id),
    FOREIGN KEY (empno) REFERENCES m_emp (empno)
);

DROP TABLE IF EXISTS event;
CREATE TABLE IF NOT EXISTS event
(
    event_id   bigint(20) AUTO_INCREMENT,
    event_name varchar(255) DEFAULT NULL,
    PRIMARY KEY (event_id)
);

DROP TABLE IF EXISTS m_emp_event;
CREATE TABLE IF NOT EXISTS m_emp_event
(
    empno    bigint(20),
    event_id bigint(20),
    FOREIGN KEY (empno) REFERENCES m_emp (empno),
    FOREIGN KEY (event_id) REFERENCES event (event_id)
);

DROP TABLE IF EXISTS user_event_association;
DROP TABLE IF EXISTS user_group_association;
DROP TABLE IF EXISTS user_status;
DROP TABLE IF EXISTS user_group;
DROP TABLE IF EXISTS user_status_master;
DROP TABLE IF EXISTS asakatu_event;
DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user(
    user_id INT PRIMARY KEY AUTO_INCREMENT,
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
     asakatu_event_id INT PRIMARY KEY AUTO_INCREMENT,
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
    asakatu_event_id INT,
    event_canceled BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (asakatu_event_id) REFERENCES asakatu_event(asakatu_event_id)
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
    asakatu_event_id INT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (asakatu_event_id) REFERENCES asakatu_event(asakatu_event_id),
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