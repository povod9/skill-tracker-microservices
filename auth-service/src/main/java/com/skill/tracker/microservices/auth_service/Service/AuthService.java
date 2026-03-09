package com.skill.tracker.microservices.auth_service.Service;

import com.skill.tracker.microservices.auth_service.Dto.LoginResponse;
import com.skill.tracker.microservices.auth_service.Entities.RequestLogin;
import com.skill.tracker.microservices.auth_service.Entities.User;
import com.skill.tracker.microservices.auth_service.Dto.UserDto;
import com.skill.tracker.microservices.auth_service.Entities.UserEntity;
import com.skill.tracker.microservices.auth_service.Entities.enums.Role;
import com.skill.tracker.microservices.auth_service.Exceptions.EmailAlreadyExistsException;
import com.skill.tracker.microservices.auth_service.Exceptions.InvalidCredentialsException;
import com.skill.tracker.microservices.auth_service.Mapper.UserMapper;
import com.skill.tracker.microservices.auth_service.Repository.UserRepository;
import com.skill.tracker.microservices.auth_service.Security.JwtCore;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final JwtCore jwtCore;

    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder, UserMapper mapper, JwtCore jwtCore) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.jwtCore = jwtCore;
    }

    public UserDto registerUser(
            User userToCreate
    )
    {
        if(repository.existsByEmail(userToCreate.email())){
            throw new EmailAlreadyExistsException("This email already registered in system");
        }

        UserEntity userEntity = new UserEntity(
                null,
                userToCreate.name(),
                userToCreate.email(),
                passwordEncoder.encode(userToCreate.password()),
                Role.USER
        );

        repository.save(userEntity);
        return mapper.toDto(userEntity);
    }

    public LoginResponse loginUser(
            RequestLogin login
    )
    {
        UserEntity userEntity = repository.findByEmail(login.email())
                .orElseThrow(() -> new EntityNotFoundException("Cannot found by email"));

        if(passwordEncoder.matches(login.password(), userEntity.getPassword())){

            LoginResponse loginResponse = new LoginResponse(jwtCore.generateToken(userEntity),
                    "Bearer");
            return loginResponse;

        }else {
            throw new InvalidCredentialsException("Wrong password or email");
        }
    }

    public UserDto getMyInfo(String email) {

        if(email == null){
            throw new IllegalArgumentException("Email cannot be null");
        }

        UserEntity userEntity = repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find by email " + email));

        return mapper.toDto(userEntity);
    }
}
