package hello.repository;

import hello.entity.Employee;
import hello.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findEventsByEmployeeListIn(Employee employee);

    @Query(value = "SELECT e.event_id, e.event_name FROM event AS e LEFT JOIN m_emp_event AS me ON me.event_id = e.event_id WHERE me.empno = :EID", nativeQuery = true)
    List<Event> findEventListByEmployeeId(@Param("EID") Long employeeId);
}
