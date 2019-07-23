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

DROP TABLE IF EXISTS test_event;
CREATE TABLE IF NOT EXISTS test_event
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
    FOREIGN KEY (event_id) REFERENCES test_event (event_id)
);

DROP TABLE IF EXISTS user_event_association;
DROP TABLE IF EXISTS user_group_association;
DROP TABLE IF EXISTS user_status;
DROP TABLE IF EXISTS user_group;
DROP TABLE IF EXISTS user_status_master;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS user;