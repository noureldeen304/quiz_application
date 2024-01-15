package com.example.quiz_application.controllers.admin;

import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_application.dto.admin.ModelAnswerDTO;
import com.example.quiz_application.dto.admin.QuizAdminDTO;
import com.example.quiz_application.dto.admin.QuizScoreAdminDTO;
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
    public ResponseEntity<List<QuizAdminDTO>> getAllOfQuizzes() {
        return ResponseEntity.ok().body(quizService.getAllOfQuizzes());
    }
    
    @GetMapping("/my-quizzes")
    public ResponseEntity<List<QuizAdminDTO>> getAllOfAdminQuizzes() {
        return ResponseEntity.ok().body(quizService.getAllOfAdminQuizzes());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<QuizAdminDTO> getQuizById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(quizService.getQuizById(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuizAdminDTO>> getQuizzesByCategoryName(@PathVariable String category) {
        return ResponseEntity.ok().body(quizService.getQuizzesByCategoryName(category));
    }

    @GetMapping("category/{category}/version/{version}")
    public ResponseEntity<QuizAdminDTO> getQuizzesByCategoryNameAndVersion(@PathVariable String category,
            @PathVariable String version) {
        return ResponseEntity.ok().body(quizService.getQuizzesByCategoryNameAndVersion(category, version));
    }

    @GetMapping("/id/{id}/model-answers")
    public ResponseEntity<List<ModelAnswerDTO>> getQuizModelAnswers(@PathVariable Integer id) {
        List<ModelAnswerDTO> answerSubmissionDTOs = quizService.getQuizModelAnswers(id);
        return ResponseEntity.ok().body(answerSubmissionDTOs);
    }

    @GetMapping("/id/{id}/students-scores")
    public ResponseEntity<List<QuizScoreAdminDTO>> getStudentScoresInQuiz(@PathVariable Integer id) {
        List<QuizScoreAdminDTO> quizScoreAdminDTOs = quizService.getStudentScoresInQuiz(id);

        return ResponseEntity.ok().body(quizScoreAdminDTOs);
    }

    @PostMapping(value = "/create-quiz-with-random-questions")
    public ResponseEntity<QuizAdminDTO> createQuizWithRandomQuestions(@RequestParam String categoryName,
            @RequestParam Integer noOfQuestions,
            @RequestParam String version) {
        QuizAdminDTO quiz = quizService.createQuizWithRandomQuestions(categoryName, noOfQuestions, version);
        return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
    }

    @PostMapping(value = "/create-quiz-with-selected-questions")
    public ResponseEntity<QuizAdminDTO> createQuizWithSelectedQuestionsIds(@RequestParam String categoryName,
            @RequestParam String version,
            @RequestParam String ids) {
        QuizAdminDTO quizAdminDTO = quizService.createQuizWithSelectedQuestionsIds(categoryName, version, ids);
        return ResponseEntity.status(HttpStatus.CREATED).body(quizAdminDTO);
    }

    @PutMapping("/update-quiz/{id}/update-version")
    public ResponseEntity<QuizAdminDTO> updateVersionOfQuiz(@PathVariable Integer id,
            @RequestParam String version) {

        QuizAdminDTO quizAdminDTO = quizService.updateVersionOfQuiz(id, version);
        return ResponseEntity.status(HttpStatus.OK).body(quizAdminDTO);
    }

    @PutMapping("/update-quiz/{id}/add-random-questions")
    public ResponseEntity<QuizAdminDTO> addNumberOfRandomQuestions(@PathVariable Integer id,
            @RequestParam Integer noOfQuestions) {

        QuizAdminDTO quizAdminDTO = quizService.addNumberOfRandomQuestionsToQuiz(id, noOfQuestions);
        return ResponseEntity.status(HttpStatus.OK).body(quizAdminDTO);
    }

    @PutMapping("/update-quiz/{id}/add-selected-questions")
    public ResponseEntity<QuizAdminDTO> addNumberOfSelectedQuestionsToQuiz(@PathVariable Integer id,
            @RequestParam String ids) {

        QuizAdminDTO quizAdminDTO = quizService.addNumberOfSelectedQuestionsToQuiz(id, ids);
        return ResponseEntity.status(HttpStatus.OK).body(quizAdminDTO);
    }

    @PutMapping("/update-quiz/{id}/remove-random-questions")
    public ResponseEntity<QuizAdminDTO> removeNumberOfRandomQuestionsFromQuiz(@PathVariable Integer id,
            @RequestParam Integer noOfQuestions) {

        QuizAdminDTO quizAdminDTO = quizService.removeNumberOfRandomQuestionsFromQuiz(id, noOfQuestions);
        return ResponseEntity.status(HttpStatus.OK).body(quizAdminDTO);
    }

    @PutMapping("/update-quiz/{id}/remove-selected-questions")
    public ResponseEntity<QuizAdminDTO> removeSelectedNumberOfSelectedQuestionsFromQuiz(@PathVariable Integer id,
            @RequestParam String ids) {

        QuizAdminDTO quizAdminDTO = quizService.removeSelectedQuestionsFromQuiz(id, ids);
        return ResponseEntity.status(HttpStatus.OK).body(quizAdminDTO);
    }

    @PutMapping("/update-quiz/{id}/remove-all-questions")
    public ResponseEntity<QuizAdminDTO> removeAllQuestionsFromQuizById(@PathVariable Integer id) {

        QuizAdminDTO quizAdminDTO = quizService.removeAllQuestionsFromQuizById(id);
        return ResponseEntity.status(HttpStatus.OK).body(quizAdminDTO);
    }

    @DeleteMapping("/remove-quiz/{id}")
    public ResponseEntity<QuizAdminDTO> removeQuizById(@PathVariable Integer id) {
        QuizAdminDTO quizAdminDTO = quizService.removeQuizById(id);
        return ResponseEntity.status(HttpStatus.OK).body(quizAdminDTO);
    }
}