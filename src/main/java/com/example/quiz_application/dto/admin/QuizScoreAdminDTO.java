package com.example.quiz_application.dto.admin;

import lombok.Data;

@Data
public class QuizScoreAdminDTO {
    private String student;
    private String username;
    private Integer score;
    private Integer overallScore;
}