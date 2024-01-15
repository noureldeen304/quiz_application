package com.example.quiz_application.controllers.student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_application.dto.student.AnswerSubmissionDTO;
import com.example.quiz_application.dto.student.QuizScoreStudentDTO;
import com.example.quiz_application.dto.student.QuizStudentDTO;
import com.example.quiz_application.services.student.QuizStudentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/student/quizzes")
public class QuizStudentController {

    @Autowired
    QuizStudentService quizStudentService;

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<QuizStudentDTO>> getQuizzesByCategory(@PathVariable String categoryName) {

        List<QuizStudentDTO> quizStudentDTOs = quizStudentService.retrieveQuiz(categoryName);
        return ResponseEntity.ok().body(quizStudentDTOs);

    }

    @GetMapping("/my-quiz-scores")
    public ResponseEntity<List<QuizScoreStudentDTO>> showQuestionsWithModelAndSubmittedAnswers() {

        List<QuizScoreStudentDTO> quizScoreStudentDTOs = quizStudentService.retrieveStudentQuizScores();
        return ResponseEntity.status(HttpStatus.OK).body(quizScoreStudentDTOs);
    }

    @GetMapping("/id/{id}/take-quiz")
    public ResponseEntity<QuizStudentDTO> getQuizzesByCategory(@PathVariable Integer id) {

        QuizStudentDTO quizStudentDTOs = quizStudentService.retrieveQuizById(id);
        return ResponseEntity.ok().body(quizStudentDTOs);

    }

    @PostMapping("/id/{id}/submit-answers")
    public ResponseEntity<String> submitAnswers(@PathVariable Integer id,
            @RequestBody List<AnswerSubmissionDTO> answerSubmissions) {

        Integer score = quizStudentService.submitAnswers(id, answerSubmissions);

        return ResponseEntity.ok().body("Answers submitted successfully\nYour score: " + score);
    }

}