package com.example.quiz_application.exceptions;

public class InvalidInputDataExceptionWithSuggestions extends InvalidInputDataException {

    public InvalidInputDataExceptionWithSuggestions(String inputName, String wrongValue, String availableValues) {
        super("There is no " + inputName + " named: " + wrongValue + "\nAvailable values: " + availableValues);
    }

}
