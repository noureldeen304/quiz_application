package com.example.quiz_application.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_application.dto.admin.QuizAdminDTO;
import com.example.quiz_application.services.QuizService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "admin/quizzes")
public class QuizAdminController {
    @Autowired
    QuizService quizService;

    @GetMapping
    public List<QuizAdminDTO> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @PostMapping(value = "/create-quiz-randomly")
    public ResponseEntity<String> createQuizWithRandomQuestions(@RequestParam String categoryName,
            @RequestParam Integer noOfQuestions,
            @RequestParam String version) {
        quizService.createQuizRandomly(categoryName, noOfQuestions, version);
        return ResponseEntity.status(HttpStatus.CREATED).body("The quiz created successfully");
    }

    @PostMapping(value = "/create-quiz")
    public ResponseEntity<String> createQuizWithSpecificQuestionsIds(@RequestParam String categoryName,
            @RequestParam String version,
            String ids) {
        quizService.createQuizWithSpecificQuestionsIds(categoryName, version, ids);
        return ResponseEntity.status(HttpStatus.CREATED).body("The quiz created successfully");
    }

    @PutMapping("/update-quiz/{id}")
    public ResponseEntity<String> updateQuizById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @DeleteMapping("/remove-quiz/{id}")
    public ResponseEntity<String> removeQuizById(@PathVariable Integer id) {
        quizService.removeQuizById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Quiz deleted successfully");
    }
}