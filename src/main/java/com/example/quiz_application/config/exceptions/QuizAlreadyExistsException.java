package com.example.quiz_application.config.exceptions;

public class QuizAlreadyExistsException extends RuntimeException {

    public QuizAlreadyExistsException(String categoryName, String version, Integer id) {
        super("Quiz with category {" + categoryName + "} " + "and version {" + version + "} already exists with ID: {"
                + id + "}\nPlease use a different version.");
    }

}