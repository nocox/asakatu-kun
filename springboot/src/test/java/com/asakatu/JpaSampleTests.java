package com.asakatu;

import com.asakatu.entity.Employee;
import com.asakatu.entity.Phone;
import com.asakatu.repository.EmployeeRepository;
import com.asakatu.repository.PhoneRepository;
import com.asakatu.entity.TestEvent;
import com.asakatu.repository.TestEventRepository;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaSampleTests {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    TestEventRepository testEventRepository;

    @Test
    public void testFindById() {
        Optional<Employee> findEmployee = employeeRepository.findById(1L);
        assert findEmployee.isPresent();
        Assert.assertThat(findEmployee.get().getId(), Is.is(1L));
    }

    @Test
    public void testFindByEmpname() {
        Optional<Employee> findEmployee = employeeRepository.findByEmpname("従業員B");
        assert findEmployee.isPresent();
        Assert.assertThat(findEmployee.get().getId(), Is.is(2L));
    }

    @Test
    public void testFindPhoneListByEmployeeId() {
        List<Phone> phoneList = phoneRepository.findPhonesByEmpId(1L);

        Assert.assertThat(phoneList.size(), Is.is(2));
        Assert.assertThat(phoneList.get(0).getNumber(), Is.is("090-0000-0000"));
        Assert.assertThat(phoneList.get(1).getNumber(), Is.is("090-1111-1111"));
    }

    @Test
    public void testFindPhoneListByEmployee() {
        Optional<Employee> findEmployee = employeeRepository.findById(1L);
        assert findEmployee.isPresent();
        List<Phone> phoneList = phoneRepository.findPhonesByEmp(findEmployee.get());

        Assert.assertThat(phoneList.size(), Is.is(2));
        Assert.assertThat(phoneList.get(0).getNumber(), Is.is("090-0000-0000"));
        Assert.assertThat(phoneList.get(1).getNumber(), Is.is("090-1111-1111"));
    }

    @Test
    public void testFindPhoneListByEmployeeIdAtAutoQuery() {
        List<Phone> phoneList = phoneRepository.findPhonesByEmployee_Id(1L);

        Assert.assertThat(phoneList.size(), Is.is(2));
        Assert.assertThat(phoneList.get(0).getNumber(), Is.is("090-0000-0000"));
        Assert.assertThat(phoneList.get(1).getNumber(), Is.is("090-1111-1111"));
    }

    @Test
    public void testFindEventListByEmployeeAtNativeQuery() {
        List<TestEvent> eventByEmployeeListIn = testEventRepository.findEventListByEmployeeId(1L);
        Assert.assertThat(eventByEmployeeListIn.size(), Is.is(2));
        Assert.assertThat(eventByEmployeeListIn.get(0).getEventName(), Is.is("asakatu_vol1"));
        Assert.assertThat(eventByEmployeeListIn.get(1).getEventName(), Is.is("hirukatu_vol1"));
    }

    @Test
    public void testFindEventListByEmployeeAtAutoQuery() {
        Optional<Employee> findEmployee = employeeRepository.findByEmpname("従業員C");
        assert findEmployee.isPresent();
        List<TestEvent> eventByEmployeeListIn = testEventRepository.findEventsByEmployeeListIn(findEmployee.get());
        Assert.assertThat(eventByEmployeeListIn.size(), Is.is(1));
        Assert.assertThat(eventByEmployeeListIn.get(0).getEventName(), Is.is("hirukatu_vol1"));
    }

    @Test
    public void testFindEmployeeListByEvent() {
        Optional<TestEvent> event = testEventRepository.findById(2L);
        assert event.isPresent();
        Assert.assertThat(event.get().getEventName(), Is.is("hirukatu_vol1"));
        List<Employee> employeeList = employeeRepository.findEmployeesByEventListIn(event.get());

        Assert.assertThat(employeeList.size(), Is.is(3));
        Assert.assertThat(employeeList.get(0).getEmpname(), Is.is("従業員A"));
        Assert.assertThat(employeeList.get(1).getEmpname(), Is.is("従業員B"));
        Assert.assertThat(employeeList.get(2).getEmpname(), Is.is("従業員C"));
    }
}
