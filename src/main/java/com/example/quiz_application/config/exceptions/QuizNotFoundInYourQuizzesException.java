package com.example.quiz_application.config.exceptions;

public class QuizNotFoundInYourQuizzesException extends RuntimeException {

    public QuizNotFoundInYourQuizzesException(Integer id) {
        super("Quiz with ID {" + id + "} does not exist in your quizzes.");
    }

}
