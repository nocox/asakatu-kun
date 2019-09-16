package com.asakatu.repository;

import com.asakatu.entity.UserStatusMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusMasterRepository extends JpaRepository<UserStatusMaster, Long> {
}