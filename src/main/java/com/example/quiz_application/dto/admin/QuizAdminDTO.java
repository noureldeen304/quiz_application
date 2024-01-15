package com.example.quiz_application.dto.admin;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizAdminDTO {
    private Integer id;
    private String author;
    private Integer noOfQuestions;
    private String version;
    private String categoryName;
    private List<QuestionAdminDTO> questions;
}
