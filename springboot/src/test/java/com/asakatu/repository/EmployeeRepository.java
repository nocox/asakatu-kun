package com.asakatu.repository;

import com.asakatu.entity.Employee;
import com.asakatu.entity.TestEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    Optional<Employee> findByEmpname(String empname);

    List<Employee> findEmployeesByEventListIn(TestEvent testEvent);
}
