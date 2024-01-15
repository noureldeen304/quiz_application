package com.example.quiz_application.services.student;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.quiz_application.config.exceptions.CategoryNotFoundException;
import com.example.quiz_application.config.exceptions.QuizNotFoundException;
import com.example.quiz_application.config.exceptions.StudentHasNoStoredScores;
import com.example.quiz_application.config.exceptions.UserNotFoundException;
import com.example.quiz_application.dto.student.AnswerSubmissionDTO;
import com.example.quiz_application.dto.student.QuizScoreStudentDTO;
import com.example.quiz_application.dto.student.QuizStudentDTO;
import com.example.quiz_application.mapper.Mapper;
import com.example.quiz_application.model.Category;
import com.example.quiz_application.model.Quiz;
import com.example.quiz_application.model.Student;
import com.example.quiz_application.model.QuizScore;
import com.example.quiz_application.repositories.CategoryRepository;
import com.example.quiz_application.repositories.QuizRepository;
import com.example.quiz_application.repositories.QuizScoreRepository;
import com.example.quiz_application.repositories.StudentRepository;

@Service
public class QuizStudentService {
    @Autowired
    private Mapper mapper;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private QuizScoreRepository quizScoreRepository;
    @Autowired
    private StudentRepository studentRepository;

    private final String ROLE = "Student";

    public List<QuizStudentDTO> retrieveQuiz(String categoryName) {

        Category categoryEntity = categoryRepository.findByCategoryName(capitalizeFirstLetter(categoryName))
                .orElseThrow(() -> new CategoryNotFoundException(categoryName));

        List<Quiz> quizs = categoryEntity.getQuizzes();
        return mapper.mapList(quizs, QuizStudentDTO.class);
    }

    public QuizStudentDTO retrieveQuizById(Integer id) {

        Quiz quizEntity = quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(id));

        return mapper.map(quizEntity, QuizStudentDTO.class);
    }

    public List<QuizScoreStudentDTO> retrieveStudentQuizScores() {
        String username = getAuthentication().getName();
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(ROLE, username));

        List<QuizScore> quizScores = quizScoreRepository.findByStudent(student)
                .orElseThrow(() -> new StudentHasNoStoredScores(username));

        return mapper.mapList(quizScores, QuizScoreStudentDTO.class);
    }

    public Integer submitAnswers(Integer id, List<AnswerSubmissionDTO> userSubmissions) {

        Quiz quizEnity = quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(id));

        List<AnswerSubmissionDTO> correctAnswers = mapper.mapList(quizEnity.getQuestions(),
                AnswerSubmissionDTO.class);

        Integer score = calculateScore(userSubmissions, correctAnswers);

        String username = getAuthentication().getName();
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(ROLE, username));

        QuizScore quizScore = QuizScore.builder().student(student).score(score).quiz(quizEnity).build();
        quizScoreRepository.save(quizScore);

        return score;
    }

    private int calculateScore(List<AnswerSubmissionDTO> userSubmissions, List<AnswerSubmissionDTO> correctAnswers) {
        int score = 0;

        Map<Long, Integer> correctAnswerMap = correctAnswers.stream()
                .collect(Collectors.toMap(AnswerSubmissionDTO::getQuestionId,
                        AnswerSubmissionDTO::getSelectedChoiceNumber));

        for (AnswerSubmissionDTO userSubmission : userSubmissions) {
            Long questionId = userSubmission.getQuestionId();
            Integer userSelectedChoice = userSubmission.getSelectedChoiceNumber();

            if (correctAnswerMap.containsKey(questionId)) {
                if (userSelectedChoice == correctAnswerMap.get(questionId)) {
                    score++;
                }
            }
        }

        return score;
    }

    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    private Authentication getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .orElseThrow(() -> new IllegalStateException("Authentication Not Found."));
    }

}
