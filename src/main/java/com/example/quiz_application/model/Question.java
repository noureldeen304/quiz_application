package com.example.quiz_application.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.quiz_application.enums.DifficultyLevel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Question {
    @Id
    @SequenceGenerator(name = "question_sequence", sequenceName = "question_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_sequence")
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    Category category;

    @Column(name = "difficulty_level")
    @Enumerated(EnumType.STRING)
    DifficultyLevel difficultyLevel;

    @Column(name = "question_title", unique = true)
    String questionTitle;
    String option1;
    String option2;
    String option3;
    String option4;
    String answer;

    public Integer getAnswerNumber() {
        if (answer.equals(option1)) {
            return 1;
        } else if (answer.equals(option2)) {
            return 2;
        } else if (answer.equals(option3)) {
            return 3;
        }
        return 4;
    }
}
