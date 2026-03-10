package com.skill.tracker.microservices.skill_service.Repository;

import com.skill.tracker.microservices.skill_service.Entity.UserSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkillEntity,Long> {

    Optional<UserSkillEntity> findAllByUserId(Long userId);

    boolean existsByUserIdAndSkill_Id(Long userId, Long skillId);
}
