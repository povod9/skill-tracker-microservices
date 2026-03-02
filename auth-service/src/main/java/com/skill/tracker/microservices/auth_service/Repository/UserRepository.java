package com.skill.tracker.microservices.auth_service.Repository;

import com.skill.tracker.microservices.auth_service.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
