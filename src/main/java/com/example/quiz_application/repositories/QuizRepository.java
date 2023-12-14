package com.example.quiz_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz_application.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    // Optional<List<Quiz>> findByCategory(String category);

    // @Query(nativeQuery = true, value = "")
    // Optional<Quiz> findByCategoryAndVersion(String category, String version);
}
