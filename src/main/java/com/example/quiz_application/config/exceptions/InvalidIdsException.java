package com.example.quiz_application.config.exceptions;

public class InvalidIdsException extends RuntimeException {

    public InvalidIdsException(String invalidInput) {
        super("Invalid ID format: " + invalidInput + ". \nPlease provide numerical values separated by commas for the 'ids' parameter.");
    }

}