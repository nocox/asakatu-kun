CREATE TABLE IF NOT EXISTS m_emp
(
    empno        bigint(20) AUTO_INCREMENT,
    empname      varchar(255) DEFAULT NULL,
    departmentid varchar(10)  DEFAULT NULL,
    PRIMARY KEY (empno)
);

CREATE TABLE IF NOT EXISTS phone
(
    phone_id bigint(20) AUTO_INCREMENT,
    number   varchar(255),
    empno    bigint,
    PRIMARY KEY (phone_id),
    FOREIGN KEY (empno) REFERENCES m_emp (empno)
);

CREATE TABLE IF NOT EXISTS event
(
    event_id   bigint(20) AUTO_INCREMENT,
    event_name varchar(255) DEFAULT NULL,
    PRIMARY KEY (event_id)
);


CREATE TABLE IF NOT EXISTS m_emp_event
(
    empno    bigint(20),
    event_id bigint(20),
    FOREIGN KEY (empno) REFERENCES m_emp (empno),
    FOREIGN KEY (event_id) REFERENCES event (event_id)
);
