package com.example.quiz_application.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.quiz_application.model.Question;
import com.example.quiz_application.enums.DifficultyLevel;
import com.example.quiz_application.model.Category;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Optional<List<Question>> findByDifficultyLevel(DifficultyLevel difficultylevel);

    Optional<List<Question>> findAllByCategory(Category category);

    Optional<Question> findByQuestionTitle(String questionTitle);

    @Query(nativeQuery = true, value = "SELECT * FROM QUESTION S WHERE S.CATEGORY_id = :categoryId ORDER BY RANDOM() LIMIT :noOfQuestions")
    Optional<List<Question>> getRandomQuestionsOfSpecificCategory(Integer categoryId, Integer noOfQuestions);

}
