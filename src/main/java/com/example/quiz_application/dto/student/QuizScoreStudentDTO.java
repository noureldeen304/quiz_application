package com.example.quiz_application.dto.student;

import lombok.Data;

@Data
public class QuizScoreStudentDTO {
    private Integer quizId;
    private String categoryName;
    private String version;
    private Integer score;
    private Integer overallScore;
}
