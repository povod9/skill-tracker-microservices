package com.skill.tracker.microservices.skill_service.Dto;

public record SkillCreateRequestDto(
        String name,
        String description
) {
}
