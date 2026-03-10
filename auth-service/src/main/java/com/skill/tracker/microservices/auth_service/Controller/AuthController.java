package com.skill.tracker.microservices.auth_service.Controller;

import com.skill.tracker.microservices.auth_service.Dto.LoginResponse;
import com.skill.tracker.microservices.auth_service.Dto.PrincipalDto;
import com.skill.tracker.microservices.auth_service.Entities.RequestLogin;
import com.skill.tracker.microservices.auth_service.Entities.User;
import com.skill.tracker.microservices.auth_service.Dto.UserDto;
import com.skill.tracker.microservices.auth_service.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(
            @RequestBody @Valid User userToCreate
    )
    {
        var createdUser = service.registerUser(userToCreate);

        return ResponseEntity
                .status(201)
                .body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
           @RequestBody @Valid RequestLogin login
    )
    {
        LoginResponse loginResponse = service.loginUser(login);

        return ResponseEntity
                .ok()
                .body(loginResponse);

    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMyInfo(
            Authentication authentication
    )
    {
        Object principalDto = authentication.getPrincipal();

        return ResponseEntity
                .ok()
                .body(service.getMyInfo(((PrincipalDto) principalDto).email()));
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {


        return ResponseEntity
                .ok()
                .body(service.getAllUsers());
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<UserDto> deleteUserById(
            @PathVariable Long id
    )
    {

        return ResponseEntity
                .ok()
                .body(service.deleteUser(id));
    }


}
