package com.example.quiz_application.dto.student;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizStudentDTO {
    private Integer id;
    private String author;
    private String categoryName;
    private String version;
    private Integer noOfQuestions;
    private List<QuestionStudentDTO> questions;
}
