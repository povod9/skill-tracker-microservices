package com.skill.tracker.microservices.auth_service.Entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestLogin(
        @Email
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
