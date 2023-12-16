package com.example.quiz_application.controllers.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.quiz_application.dto.user.QuizUserDTO;
import com.example.quiz_application.dto.user.SubmittedQuizUserDTO;
import com.example.quiz_application.services.user.QuizUserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user/quiz")
public class QuizUserController {
    @Autowired
    QuizUserService quizUserService;

    @GetMapping
    public QuizUserDTO getQuiz(@RequestParam(required = true) String categoryName,
            @RequestParam(required = true) String version) {

        return quizUserService.getQuiz(categoryName, version);

    }

    @PostMapping("/submit-answers")
    public void submitAnswers(@RequestParam(required = true) String categoryName,
            @RequestParam(required = true) String version, @RequestBody(required = true) List<String> userAnswers,
            RedirectAttributes redirectAttributes) {

        Integer score = quizUserService.submitAnswers(categoryName, version, userAnswers);

        String redirectUrl = String.format("redirect:/user/quiz/show-results?categoryName=%s&version=%s&score=%d",
                categoryName, version, score);
        // return redirectUrl;
    }

    @GetMapping("/show-results")
    public ResponseEntity<SubmittedQuizUserDTO> showQuestionsWithModelAndSubmittedAnswers(
            @RequestParam String categoryName,
            @RequestParam String version,
            @RequestParam Integer score,
            @RequestBody List<String> userAnswers) {

        SubmittedQuizUserDTO quizUserDTOAfterSubmit = quizUserService.showQuestionsWithModelAndSubmittedAnswers(
                categoryName, version,
                score, userAnswers);
        return ResponseEntity.status(HttpStatus.OK).body(quizUserDTOAfterSubmit);
    }

}