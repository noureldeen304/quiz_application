package com.example.quiz_application.config.exceptions;

public class IdsNotSelectedException extends RuntimeException {

    public IdsNotSelectedException() {
        super("Quiz creation failed: specify IDs for questions you want to add to this quiz");
    }
}