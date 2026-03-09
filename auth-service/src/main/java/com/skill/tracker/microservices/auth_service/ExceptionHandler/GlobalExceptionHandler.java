package com.skill.tracker.microservices.auth_service.ExceptionHandler;

import com.skill.tracker.microservices.auth_service.Dto.ErrorDto;
import com.skill.tracker.microservices.auth_service.Exceptions.EmailAlreadyExistsException;
import com.skill.tracker.microservices.auth_service.Exceptions.InvalidCredentialsException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> illegalArgExcept(IllegalArgumentException ex){
        log.error("Handle error", ex);

        ErrorDto errorDto =  new ErrorDto(
                "Bad Request",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> entityNotFound(EntityNotFoundException ex){
        log.error("Handle error", ex);

        ErrorDto errorDto =  new ErrorDto(
                "Entity not found by email",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDto);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> emailAlreadyExists(EmailAlreadyExistsException ex){
        log.error("Handle error", ex);

        ErrorDto errorDto =  new ErrorDto(
                "Email already registered",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorDto);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorDto> invalidCredentials(InvalidCredentialsException ex){
        log.error("Handle error", ex);

        ErrorDto errorDto =  new ErrorDto(
                "Invalid credentials",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return  ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<ErrorDto> methodArgumentNotValid(MethodArgumentNotValidException ex){
        log.error("Handle error", ex);

        ErrorDto errorDto =  new ErrorDto(
                "Method argument not valid",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return   ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public  ResponseEntity<ErrorDto> httpMessageNotReadable(HttpMessageNotReadableException ex){
        log.error("Handle error", ex);

        ErrorDto errorDto =  new ErrorDto(
                "Message not readable",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return   ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public   ResponseEntity<ErrorDto> accessDenied(AccessDeniedException ex){
        log.error("Handle error", ex);

        ErrorDto errorDto =  new ErrorDto(
                "Access denied",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(errorDto);
    }

    @ExceptionHandler(AuthenticationException.class)
    public    ResponseEntity<ErrorDto> authentication(AuthenticationException ex){
        log.error("Handle error", ex);

        ErrorDto errorDto =  new ErrorDto(
                "Authentication Failed",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorDto);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> exception(Exception ex){
        log.error("Handle error", ex);

        ErrorDto errorDto =  new ErrorDto(
                "Internal server error",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorDto);
    }
}
