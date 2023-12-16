package com.example.quiz_application.dto.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmittedQuizUserDTO {
    String categoryName;
    String version;
    Integer noOfQuestions;
    List<QuestionUserDTOAfterSubmit> questions;
}
