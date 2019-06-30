insert into m_emp
    (empname, departmentid)
values ('従業員A', '10101001')
     , ('従業員B', '10101001')
     , ('従業員C', '10101002')
     , ('従業員D', '10101003');

insert into phone
    (number, empno)
values ('090-0000-0000', 1)
     , ('090-1111-1111', 1)
     , ('080-2222-2222', 2)
     , ('080-3333-3333', 2)
     , ('080-4444-4444', 2)
     , ('080-5555-5555', 3);

insert into event
    (event_name)
values ('asakatu_vol1')
     , ('hirukatu_vol1');

insert into m_emp_event
    (empno, event_id)
values (1, 1)
     , (1, 2)
     , (2, 1)
     , (2, 2)
     , (3, 2)