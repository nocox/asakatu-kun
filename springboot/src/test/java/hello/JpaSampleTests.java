package hello;

import hello.entity.Employee;
import hello.entity.Phone;
import hello.repository.EmployeeRepository;
import hello.repository.PhoneRepository;
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

    @Test
    public void testFindById() {
        Optional<Employee> findEmployee = employeeRepository.findById(5L);
        assert findEmployee.isPresent();
        Assert.assertThat(findEmployee.get().getId(), Is.is(5L));
    }

    @Test
    public void testFindByEmpname() {
        Optional<Employee> findEmployee = employeeRepository.findByEmpname("従業員B");
        assert findEmployee.isPresent();
        Assert.assertThat(findEmployee.get().getId(), Is.is(6L));
    }

    @Test
    public void testFindPhoneListByEmployeeId() {
        List<Phone> phoneList = phoneRepository.findPhonesByEmpId(5L);

        Assert.assertThat(phoneList.size(), Is.is(2));
        Assert.assertThat(phoneList.get(0).getNumber(), Is.is("090-0000-0000"));
        Assert.assertThat(phoneList.get(1).getNumber(), Is.is("090-1111-1111"));
    }

    @Test
    public void testFindPhoneListByEmployee() {
        Optional<Employee> findEmployee = employeeRepository.findById(5L);
        assert findEmployee.isPresent();
        List<Phone> phoneList = phoneRepository.findPhonesByEmp(findEmployee.get());

        Assert.assertThat(phoneList.size(), Is.is(2));
        Assert.assertThat(phoneList.get(0).getNumber(), Is.is("090-0000-0000"));
        Assert.assertThat(phoneList.get(1).getNumber(), Is.is("090-1111-1111"));
    }

    @Test
    public void testFindPhoneListByEmployeeIdAtAutoQuery() {
        List<Phone> phoneList = phoneRepository.findPhonesByEmployee_Id(5L);

        Assert.assertThat(phoneList.size(), Is.is(2));
        Assert.assertThat(phoneList.get(0).getNumber(), Is.is("090-0000-0000"));
        Assert.assertThat(phoneList.get(1).getNumber(), Is.is("090-1111-1111"));
    }
}
