package com.skill.tracker.microservices.auth_service.Dto;

public record LoginResponse(
        String accessToken,
        String tokenType
) {
}
