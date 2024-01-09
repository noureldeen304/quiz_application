package com.example.quiz_application.services.student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz_application.dto.student.QuizStudentDTO;
import com.example.quiz_application.dto.student.SubmittedQuizStudentDTO;
import com.example.quiz_application.mapper.Mapper;
import com.example.quiz_application.model.Category;
import com.example.quiz_application.model.Question;
import com.example.quiz_application.model.Quiz;
import com.example.quiz_application.repositories.CategoryRepository;
import com.example.quiz_application.repositories.QuizRepository;

@Service
public class QuizStudentService {
    @Autowired
    Mapper mapper;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public QuizStudentDTO getQuiz(String categoryName, String version) {

        Category categoryEntity = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new IllegalStateException("There is no category with this name: " + categoryName));

        List<String> verstionsOfCategoryEntity = quizRepository.findByCategory(categoryEntity).stream()
                .map(Quiz::getVersion).collect(Collectors.toList());
        if (!verstionsOfCategoryEntity.contains(version)) {
            throw new IllegalStateException(
                    "Quiz with this version (" + version + ") doesn't exist for this category (" + categoryName + ")");
        }
        return mapper.map(quizRepository.findByCategoryAndVersion(categoryEntity, version).get(0),
                QuizStudentDTO.class);
    }

    public Integer submitAnswers(String categoryName, String version, List<String> userAnswers) {
        Category categoryEntity = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new IllegalStateException("There is no category with this name: " + categoryName));

        List<String> modelAnswers = quizRepository.findByCategoryAndVersion(categoryEntity, version).get(0)
                .getQuestions().stream().map(Question::getAnswer).collect(Collectors.toList());

        Integer score = calculateScore(userAnswers, modelAnswers);
        return score;
    }

    public static int calculateScore(List<String> userAnswers, List<String> modelAnswers) {
        if (userAnswers.size() != modelAnswers.size()) {
            throw new IllegalArgumentException("Please answer all questions.");
        }

        int score = 0;

        for (int i = 0; i < userAnswers.size(); i++) {
            String userAnswer = userAnswers.get(i);
            String modelAnswer = modelAnswers.get(i);

            if (userAnswer.equalsIgnoreCase(modelAnswer)) {
                score++;
            }
        }

        return score;
    }

    public SubmittedQuizStudentDTO showQuestionsWithModelAndSubmittedAnswers(String categoryName, String version,
            Integer score,
            List<String> userAnswers) {

        Category categoryEntity = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new IllegalStateException("There is no category with this name: " + categoryName));

        SubmittedQuizStudentDTO quizUserDTOAfterSubmit = mapper
                .map(quizRepository.findByCategoryAndVersion(categoryEntity, version).get(0),
                        SubmittedQuizStudentDTO.class);

        for (int i = 0; i < userAnswers.size(); i++) {

            quizUserDTOAfterSubmit.getQuestions().get(i).setYourAnswer(userAnswers.get(i));

        }

        return quizUserDTOAfterSubmit;
    }

}
