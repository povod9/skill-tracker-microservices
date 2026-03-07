package com.skill.tracker.microservices.auth_service.Dto;


public record PrincipalDto(
        String email,
        String role,
        Long id
) {
}
