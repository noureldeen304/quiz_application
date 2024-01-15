package com.example.quiz_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz_application.model.Quiz;
import com.example.quiz_application.model.QuizScore;
import com.example.quiz_application.model.Student;

import java.util.List;
import java.util.Optional;


@Repository
public interface QuizScoreRepository extends JpaRepository<QuizScore, Integer> {

    Optional<List<QuizScore>> findByStudent(Student student);

    Optional<List<QuizScore>> findByQuiz(Quiz quiz);
}