package com.example.quiz_application.config.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String role, String username) {
        super(role + " not found {" + username + "}\nInvalid username or password.");
    }

}