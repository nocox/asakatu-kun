package hello.entity;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @Column(name = "phone_id")
    private Long phoneId;

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "empno")
    private Employee employee;

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
