package com.example.quiz_application.services.interfaces.student;

import java.util.List;

import com.example.quiz_application.dto.student.AnswerSubmissionDTO;
import com.example.quiz_application.dto.student.QuizScoreStudentDTO;
import com.example.quiz_application.dto.student.QuizStudentDTO;

public interface IQuizStudentService {

    List<QuizStudentDTO> retrieveQuiz(String categoryName);

    List<QuizScoreStudentDTO> retrieveStudentQuizScores();

    QuizStudentDTO retrieveQuizById(Integer id);

    Integer submitAnswers(Integer id, List<AnswerSubmissionDTO> answerSubmissions);
    
}
