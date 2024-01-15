package com.example.quiz_application.config.exceptions;

import java.util.Arrays;

import com.example.quiz_application.enums.DifficultyLevel;

public class DifficultyLevelNotFoundException extends RuntimeException {

    public DifficultyLevelNotFoundException(String insertedDifficultyLevel) {
        super("There is no difficulty level" + " named: " + insertedDifficultyLevel
                + "\nAvailable values: " + Arrays.toString(DifficultyLevel.values()));
    }
}
