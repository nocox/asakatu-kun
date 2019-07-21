package com.asakatu.repository;

import com.asakatu.entity.Employee;
import com.asakatu.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    // @Query(value = " select * from phone as p where p.empno = :emp ", nativeQuery = true) // 普通のsqlでも書ける
    @Query(" select p from Phone p where p.employee.id = :id")
    List<Phone> findPhonesByEmpId(@Param("id") Long empid);

    @Query(" select p from Phone p where p.employee = :emp")
    List<Phone> findPhonesByEmp(@Param("emp") Employee employee);

    List<Phone> findPhonesByEmployee_Id(Long empid);
}
