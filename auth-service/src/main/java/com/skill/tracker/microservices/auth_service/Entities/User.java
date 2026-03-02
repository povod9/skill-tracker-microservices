package com.skill.tracker.microservices.auth_service.Entities;

import com.skill.tracker.microservices.auth_service.Entities.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record User(
        Long id,
        @NotBlank
        String name,
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        Role role
) {
}
