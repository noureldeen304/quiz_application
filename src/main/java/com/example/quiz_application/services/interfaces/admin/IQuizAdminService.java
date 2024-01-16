package com.example.quiz_application.services.interfaces.admin;

import java.util.List;

import com.example.quiz_application.dto.admin.ModelAnswerDTO;
import com.example.quiz_application.dto.admin.QuizAdminDTO;
import com.example.quiz_application.dto.admin.QuizScoreAdminDTO;

public interface IQuizAdminService {
    List<QuizAdminDTO> getAllOfQuizzes();

    List<QuizAdminDTO> getAllOfAdminQuizzes();

    List<QuizAdminDTO> getQuizzesByCategoryName(String categoryName);

    QuizAdminDTO getQuizById(Integer id);

    QuizAdminDTO getQuizzesByCategoryNameAndVersion(String categoryName, String version);

    List<QuizScoreAdminDTO> getStudentScoresInQuiz(Integer id);

    List<ModelAnswerDTO> getQuizModelAnswers(Integer id);

    QuizAdminDTO createQuizWithRandomQuestions(String categoryName, Integer noOfQuestions, String version);

    QuizAdminDTO createQuizWithSelectedQuestionsIds(String categoryName, String version,
            String stringOfIds);

    QuizAdminDTO updateVersionOfQuiz(Integer id, String version);

    QuizAdminDTO addNumberOfRandomQuestionsToQuiz(Integer id, Integer noOfQuestions);

    QuizAdminDTO addNumberOfSelectedQuestionsToQuiz(Integer id, String ids);

    QuizAdminDTO removeNumberOfRandomQuestionsFromQuiz(Integer id, Integer noOfQuestions);

    QuizAdminDTO removeSelectedQuestionsFromQuiz(Integer id, String stringOfIds);

    QuizAdminDTO removeAllQuestionsFromQuizById(Integer id);

    QuizAdminDTO removeQuizById(Integer id);
}
