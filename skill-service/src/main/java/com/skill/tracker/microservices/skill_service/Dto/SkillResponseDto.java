package com.skill.tracker.microservices.skill_service.Dto;

public record SkillResponseDto(
        Long id,
        String name,
        String description
) {
}
