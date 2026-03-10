package com.skill.tracker.microservices.skill_service.Mapper;

import com.skill.tracker.microservices.skill_service.Dto.SkillResponseDto;
import com.skill.tracker.microservices.skill_service.Entity.SkillEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    SkillResponseDto toDto(SkillEntity skill);
}
