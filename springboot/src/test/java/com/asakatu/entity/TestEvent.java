package com.asakatu.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "test_event")
public class TestEvent {
    @Id
    @Column(name = "event_id")
    private Long id;

    @Column(name = "event_name")
    private String eventName;

    @ManyToMany
    @JoinTable(name = "m_emp_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "empno"))
    private List<Employee> employeeList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEvent_name(String event_name) {
        this.eventName = event_name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
