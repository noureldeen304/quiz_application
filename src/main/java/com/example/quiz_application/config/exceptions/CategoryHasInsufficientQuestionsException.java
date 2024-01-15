package com.example.quiz_application.config.exceptions;

public class CategoryHasInsufficientQuestionsException extends RuntimeException {

    public CategoryHasInsufficientQuestionsException(String categoryName, Integer noOfRequestedQuestions, Integer noOfAvailble) {
        super("Unable to create quiz: Insufficient questions available for category '"
                + categoryName + "'. \nRequested: " + noOfRequestedQuestions + ", Available: "
                + noOfAvailble
                + ". \nPlease choose a lower number of questions or consider adding more questions to the category.");
    }

}
