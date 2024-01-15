package com.example.quiz_application.dto.student;

import lombok.Data;

@Data
public class AnswerSubmissionDTO {
    private Long questionId;
    private Integer selectedChoiceNumber;
}
