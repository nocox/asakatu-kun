package com.asakatu.repository;

import com.asakatu.entity.Event;
import com.asakatu.entity.User;
import com.asakatu.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
    UserStatus findUserStatusByEventAndUserIs(Event event, User user);

    Optional<UserStatus> findByUserIdAndEventId(Long userId, Long eventId);
}