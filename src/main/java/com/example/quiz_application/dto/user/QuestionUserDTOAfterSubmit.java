package com.example.quiz_application.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionUserDTOAfterSubmit {
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private String yourAnswer;
}
