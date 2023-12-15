package com.example.quiz_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz_application.model.Category;
import com.example.quiz_application.model.Quiz;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    List<Quiz> findByCategory(Category category);
}
