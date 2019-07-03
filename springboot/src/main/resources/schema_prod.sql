DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user
(
    user_id   bigint(20) AUTO_INCREMENT,
    user_name varchar(255) DEFAULT NULL,
    password  varchar(255) DEFAULT NULL,
    PRIMARY KEY (user_id)
);