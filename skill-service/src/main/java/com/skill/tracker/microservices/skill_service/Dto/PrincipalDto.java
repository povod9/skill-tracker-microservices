package com.skill.tracker.microservices.skill_service.Dto;

public record PrincipalDto(
        String email,
        String role,
        Long id
) {
}
