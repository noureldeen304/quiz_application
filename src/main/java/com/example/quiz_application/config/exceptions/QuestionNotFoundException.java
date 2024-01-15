package com.example.quiz_application.config.exceptions;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(Integer id) {
        super("Question with ID: " + id + " does not exist");
    }

    public QuestionNotFoundException(String categoryName, Integer insertedId) {
        super("Question with id {" + insertedId + "} " + "is not be included by category {" + categoryName + "}");
    }

    public QuestionNotFoundException(Integer questionId, Integer quizId) {
        super("Quiz with id {" + quizId + "} " + "does not include question with ID {" + questionId + "}");
    }

}
