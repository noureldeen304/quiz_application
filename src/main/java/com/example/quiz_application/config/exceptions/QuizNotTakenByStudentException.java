package com.example.quiz_application.config.exceptions;

public class QuizNotTakenByStudentException extends RuntimeException {

    public QuizNotTakenByStudentException(Integer id) {
        super("No student has taken the quiz with ID {" + id + "} yet.");
    }

}