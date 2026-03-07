package com.skill.tracker.microservices.auth_service.Mapper;

import com.skill.tracker.microservices.auth_service.Entities.User;
import com.skill.tracker.microservices.auth_service.Dto.UserDto;
import com.skill.tracker.microservices.auth_service.Entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(UserEntity userEntity);
    UserEntity toEntity(User user);
}
