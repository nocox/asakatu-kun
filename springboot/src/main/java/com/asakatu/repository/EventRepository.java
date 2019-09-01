package com.asakatu.repository;

import com.asakatu.entity.Event;
import com.asakatu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    public List<Event> findEventsByUserListIn(User user);
}