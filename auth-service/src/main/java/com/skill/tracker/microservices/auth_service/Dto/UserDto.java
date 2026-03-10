package com.skill.tracker.microservices.auth_service.Dto;

import com.skill.tracker.microservices.auth_service.Entities.enums.Role;

public record UserDto(
        String name,
        String email,
        Role role
) {

}
