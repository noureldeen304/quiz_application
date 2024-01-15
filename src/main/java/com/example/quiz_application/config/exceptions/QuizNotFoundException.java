package com.example.quiz_application.config.exceptions;

public class QuizNotFoundException extends RuntimeException {

    public QuizNotFoundException(String categoryName, String version) {
        super("Quiz with category {" + categoryName + "} " + "and version {" + version + "} does not exist");
    }

    public QuizNotFoundException(Integer id) {
        super("Quiz with ID: " + id + " does not exist");
    }

    public QuizNotFoundException(String categoryName) {
        super("Quiz with category {" + categoryName + "} does not exist");
    }

}
