package com.example.quiz_application.controllers.student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_application.dto.student.QuizStudentDTO;
import com.example.quiz_application.dto.student.SubmittedQuizStudentDTO;
import com.example.quiz_application.services.student.QuizStudentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/student/quiz")
public class QuizStudentController {
    @Autowired
    QuizStudentService quizStudentService;

    @GetMapping
    public QuizStudentDTO getQuiz(@RequestParam(required = true) String categoryName,
            @RequestParam(required = true) String version) {

        return quizStudentService.getQuiz(categoryName, version);

    }

    // @PostMapping("/submit-answers")
    // public void submitAnswers(@RequestParam(required = true) String categoryName,
    //         @RequestParam(required = true) String version, @RequestBody(required = true) List<String> studentAnswers,
    //         RedirectAttributes redirectAttributes) {

    //     Integer score = quizStudentService.submitAnswers(categoryName, version, studentAnswers);

    //     String redirectUrl = String.format("redirect:/student/quiz/show-results?categoryName=%s&version=%s&score=%d",
    //             categoryName, version, score);
    //     // return redirectUrl;
    // }

    @GetMapping("/show-results")
    public ResponseEntity<SubmittedQuizStudentDTO> showQuestionsWithModelAndSubmittedAnswers(
            @RequestParam String categoryName,
            @RequestParam String version,
            @RequestParam Integer score,
            @RequestBody List<String> studentAnswers) {

        SubmittedQuizStudentDTO quizstudentDTOAfterSubmit = quizStudentService.showQuestionsWithModelAndSubmittedAnswers(
                categoryName, version,
                score, studentAnswers);
        return ResponseEntity.status(HttpStatus.OK).body(quizstudentDTOAfterSubmit);
    }

}