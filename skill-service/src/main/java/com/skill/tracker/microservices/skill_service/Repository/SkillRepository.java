package com.skill.tracker.microservices.skill_service.Repository;

import com.skill.tracker.microservices.skill_service.Entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

    Optional<SkillEntity> findByName(String name);
    boolean existsByName(String name);
}
