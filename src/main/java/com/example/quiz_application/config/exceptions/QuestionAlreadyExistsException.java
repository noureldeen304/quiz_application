package com.example.quiz_application.config.exceptions;

public class QuestionAlreadyExistsException extends RuntimeException {

    public QuestionAlreadyExistsException() {
        super("This question already exits.");
    }

    public QuestionAlreadyExistsException(Integer questionId, Integer QuizId) {
        super("question with id {" + questionId + "} already exists in quiz with{" + QuizId + "}");
    }

}