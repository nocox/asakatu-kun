package com.asakatu.repository;

import com.asakatu.entity.Event;
import com.asakatu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findUsersByEventsListIn(Event event);
    List<User> findUsersByUsername(String username);
}
