package com.example.quiz_application.config.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String categoryName) {
        super("This category does not exist: " + categoryName);
    }

}
