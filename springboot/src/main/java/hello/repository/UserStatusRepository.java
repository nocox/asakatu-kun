package hello.repository;

import hello.entity.Event;
import hello.entity.User;
import hello.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
    public UserStatus findUserStatusByEventAndUserIs(Event event, User user);
}