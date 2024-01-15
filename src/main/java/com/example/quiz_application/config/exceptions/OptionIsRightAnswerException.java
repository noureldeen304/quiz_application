package com.example.quiz_application.config.exceptions;

public class OptionIsRightAnswerException extends RuntimeException {

    public OptionIsRightAnswerException(String option, int optionNumber) {
        super("option" + optionNumber + " \"" + option + "\" is the right answer");
    }

}