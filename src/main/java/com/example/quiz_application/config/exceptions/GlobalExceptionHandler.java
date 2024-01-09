package com.example.quiz_application.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleRegistrationException(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message;
        if (e instanceof BadCredentialsException) {
            status = HttpStatus.BAD_REQUEST;
            message = "Invalid username or password";
        } else if (e instanceof LockedException) {
            status = HttpStatus.LOCKED;
            message = "Account is locked";
        } else if (e instanceof DisabledException) {
            status = HttpStatus.FORBIDDEN;
            message = "Account is disabled";
        } else {
            message = "Authentication failed";
        }
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleInvalidInputException(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
