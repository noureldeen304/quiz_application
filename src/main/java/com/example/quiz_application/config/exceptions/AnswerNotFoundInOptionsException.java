package com.example.quiz_application.config.exceptions;

public class AnswerNotFoundInOptionsException extends RuntimeException {

    public AnswerNotFoundInOptionsException() {
        super("The answer you entered does not match any of the question's options. Please try again.");
    }

}
