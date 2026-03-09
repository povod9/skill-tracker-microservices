package com.skill.tracker.microservices.auth_service.Dto;

import java.time.LocalDateTime;

public record ErrorDto(
        String message,
        String errorMessage,
        LocalDateTime time
) {
}
