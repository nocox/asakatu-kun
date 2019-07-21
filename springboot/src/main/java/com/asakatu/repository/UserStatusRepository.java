package com.asakatu.repository;

import com.asakatu.entity.Event;
import com.asakatu.entity.User;
import com.asakatu.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
    public UserStatus findUserStatusByEventAndUserIs(Event event, User user);
}