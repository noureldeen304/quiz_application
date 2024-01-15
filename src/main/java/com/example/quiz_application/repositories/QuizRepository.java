package com.example.quiz_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz_application.model.Admin;
import com.example.quiz_application.model.Category;
import com.example.quiz_application.model.Quiz;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    Optional<List<Quiz>> findByCategory(Category category);

    Optional<Quiz> findByCategoryAndVersion(Category category, String version);

    Optional<List<Quiz>> findByAdmin(Admin admin);

    Optional<List<Quiz>> findByCategoryAndAdmin(Category category, Admin admin);
    
    Optional<Quiz> findByIdAndAdmin(Integer id, Admin admin);

    Optional<Quiz> findByAdminAndCategoryAndVersion(Admin admin, Category category, String version);
}
