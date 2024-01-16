package com.example.quiz_application.controllers.admin;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_application.dto.admin.QuestionAdminDTO;
import com.example.quiz_application.services.interfaces.admin.IQuestionAdminService;

@RestController
@RequestMapping("api/v1/admin/questions")
public class QuestionAdminController {
    @Autowired
    IQuestionAdminService service;

    @GetMapping
    public ResponseEntity<List<QuestionAdminDTO>> getAllQuestions() {
        List<QuestionAdminDTO> questions = service.getAllQuestions();
        questions.sort(Comparator.comparing(QuestionAdminDTO::getId));
        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<QuestionAdminDTO>> getQuestionByCategory(@PathVariable String category) {
        List<QuestionAdminDTO> questions = service.getQuestionsByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @GetMapping(value = "/difficultylevel/{difficultylevel}")
    public ResponseEntity<List<QuestionAdminDTO>> getQuestionBydifficultyLevel(@PathVariable String difficultylevel) {
        List<QuestionAdminDTO> questions = service.getQuestionsBydifficultyLevel(difficultylevel);
        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<QuestionAdminDTO> getQuestionById(@PathVariable Integer id) {
        QuestionAdminDTO question = service.getQuestionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(question);
    }

    @PostMapping(value = "/add_question")
    public ResponseEntity<String> addNewQuestion(@RequestBody QuestionAdminDTO question) {
        service.addNewQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping(value = "/modify/{id}")
    public ResponseEntity<QuestionAdminDTO> updateQuestion(@PathVariable Integer id,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String difficultyLevel,
            @RequestParam(required = false) String questionTitle,
            @RequestParam(required = false) String option1,
            @RequestParam(required = false) String option2,
            @RequestParam(required = false) String option3,
            @RequestParam(required = false) String option4,
            @RequestParam(required = false) String answer) {

        QuestionAdminDTO updatedQuestion = service.updateQuestion(id, categoryName, difficultyLevel, questionTitle,
                option1, option2, option3,
                option4, answer);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedQuestion);
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<QuestionAdminDTO> removeQuestion(@PathVariable Integer id) {
        QuestionAdminDTO questionAdminDTO = service.removeQuestion(id);
        return ResponseEntity.status(HttpStatus.OK).body(questionAdminDTO);
    }

}
