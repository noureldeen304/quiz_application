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
    Integer id;
    Integer noOfQuestions;
    String version;
    String categoryName;
    List<QuestionAdminDTO> questions;
}
