package com.asakatu.repository;

import com.asakatu.entity.User;
import com.asakatu.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
    @Query(value = "SELECT u FROM UserStatus u where u.eventId = :eventId and u.userId = :userId")
    Optional<UserStatus> findByEventIdAndUserId(Long eventId, Long userId);
}
