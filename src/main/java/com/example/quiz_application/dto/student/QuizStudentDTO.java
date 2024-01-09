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
    String categoryName;
    String version;
    Integer noOfQuestions;
    List<QuestionStudentDTO> questions;
}
