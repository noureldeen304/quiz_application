package com.example.quiz_application.controllers.admin;

import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_application.dto.admin.QuizAdminDTO;
import com.example.quiz_application.services.admin.QuizAdminService;

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
@RequestMapping(path = "api/v1/admin/quizzes")
public class QuizAdminController {
    @Autowired
    QuizAdminService quizService;

    @GetMapping
    public List<QuizAdminDTO> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/category/{category}")
    public List<QuizAdminDTO> getQuizzesByCategoryName(@PathVariable String category) {
        return quizService.getQuizzesByCategoryName(category);
    }

    @GetMapping("category/{category}/version/{version}")
    public QuizAdminDTO getQuizzesByCategoryNameAndVersion(@PathVariable String category,
            @PathVariable String version) {
        return quizService.getQuizzesByCategoryNameAndVersion(category, version);
    }

    @PostMapping(value = "/create-quiz-with-random-questions")
    public ResponseEntity<String> createQuizWithRandomQuestions(@RequestParam String categoryName,
            @RequestParam Integer noOfQuestions,
            @RequestParam String version) {
        quizService.createQuizWithRandomQuestions(categoryName, noOfQuestions, version);
        return ResponseEntity.status(HttpStatus.CREATED).body("Quiz created successfully");
    }

    @PostMapping(value = "/create-quiz")
    public ResponseEntity<String> createQuizWithSpecificQuestionsIds(@RequestParam String categoryName,
            @RequestParam String version,
            String ids) {
        quizService.createQuizWithSpecificQuestionsIds(categoryName, version, ids);
        return ResponseEntity.status(HttpStatus.CREATED).body("Quiz created successfully");
    }

    @PutMapping("/update-quiz/{id}/update-version")
    public ResponseEntity<String> updateVersionOfQuiz(@PathVariable Integer id,
            @RequestParam(required = false) String version) {

        quizService.updateVersionOfQuiz(id, version);
        return ResponseEntity.status(HttpStatus.OK).body("Quiz updated successfully");
    }

    @PutMapping("/update-quiz/{id}/add-random-questions")
    public ResponseEntity<String> addSpecificNumberOfRandomQuestionsToQuiz(@PathVariable Integer id,
            @RequestParam(required = false) Integer noOfQuestions) {

        quizService.addSpecificNumberOfRandomQuestionsToQuiz(id, noOfQuestions);
        return ResponseEntity.status(HttpStatus.OK).body("Quiz updated successfully");
    }

    @PutMapping("/update-quiz/{id}/remove-random-questions")
    public ResponseEntity<String> removeSpecificNumberOfRandomQuestionsFromQuiz(@PathVariable Integer id,
            @RequestParam(required = false) Integer noOfQuestions) {

        quizService.removeSpecificNumberOfRandomQuestionsFromQuiz(id, noOfQuestions);
        return ResponseEntity.status(HttpStatus.OK).body("Quiz updated successfully");
    }

    @PutMapping("/update-quiz/{id}/add-specific-questions")
    public ResponseEntity<String> addSpecificNumberOfSelectedQuestionsToQuiz(@PathVariable Integer id,
            @RequestParam(required = false) String ids) {

        quizService.addSpecificNumberOfSelectedQuestionsToQuiz(id, ids);
        return ResponseEntity.status(HttpStatus.OK).body("Quiz updated successfully");
    }

    @PutMapping("/update-quiz/{id}/remove-specific-questions")
    public ResponseEntity<String> removeSpecificNumberOfSelectedQuestionsFromQuiz(@PathVariable Integer id,
            @RequestParam(required = false) String ids) {

        quizService.removeSpecificNumberOfSelectedQuestionsFromQuiz(id, ids);
        return ResponseEntity.status(HttpStatus.OK).body("Quiz updated successfully");
    }

    @PutMapping("/update-quiz/{id}/remove-all-questions")
    public ResponseEntity<String> removeAllQuestionsFromQuizById(@PathVariable Integer id) {

        quizService.removeAllQuestionsFromQuizById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Quiz updated successfully");
    }

    @DeleteMapping("/remove-quiz/{id}")
    public ResponseEntity<String> removeQuizById(@PathVariable Integer id) {
        quizService.removeQuizById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Quiz deleted successfully");
    }
}