package hello.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "m_emp")
public class Employee {
    @Id
    @Column(name = "empno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empname;

    @OneToMany(mappedBy = "employee")
    private List<Phone> phonesList;

    @ManyToMany(mappedBy = "employeeList")
    private List<TestEvent> eventList = new ArrayList<>();

    public List<TestEvent> getEventList() {
        return eventList;
    }

    public Long getId() {
        return id;
    }

    public List<Phone> getPhonesList() {
        return phonesList;
    }

    public void setPhonesList(List<Phone> phonesList) {
        this.phonesList = phonesList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }
}
