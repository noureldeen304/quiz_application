package com.example.quiz_application.config.exceptions;

public class StudentHasNoStoredScores extends RuntimeException {

    public StudentHasNoStoredScores(String username) {
        super("Dear " + username + ", you have not taken any quizzes yet.");
    }

}