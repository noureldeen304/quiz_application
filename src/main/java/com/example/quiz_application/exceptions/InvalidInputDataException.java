package com.example.quiz_application.exceptions;

public class InvalidInputDataException extends RuntimeException {

    public InvalidInputDataException(String item, String wrongValue) {
        super("There is no " + item + " named: " + wrongValue);
    }

    public InvalidInputDataException(String message) {
        super(message);
    }

}