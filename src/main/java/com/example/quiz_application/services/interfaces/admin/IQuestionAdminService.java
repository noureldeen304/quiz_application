package com.example.quiz_application.services.interfaces.admin;

import java.util.List;
import com.example.quiz_application.dto.admin.QuestionAdminDTO;

public interface IQuestionAdminService {
    List<QuestionAdminDTO> getAllQuestions();

    List<QuestionAdminDTO> getQuestionsByCategory(String category);

    List<QuestionAdminDTO> getQuestionsBydifficultyLevel(String difficultyLevel);

    QuestionAdminDTO getQuestionById(Integer id);

    void addNewQuestion(QuestionAdminDTO questionAdminDTO);

    QuestionAdminDTO updateQuestion(Integer id,
            String categoryName,
            String difficultyLevel,
            String questionTitle,
            String option1,
            String option2,
            String option3,
            String option4,
            String answer);

    QuestionAdminDTO removeQuestion(Integer id);
}
