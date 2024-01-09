package com.example.quiz_application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "student_quiz_scores")
public class StudentQuizScore {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_quiz_score_sequence")
    @SequenceGenerator(name = "student_quiz_score_sequence", sequenceName = "student_quiz_score_sequence", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private Integer score; // Store the student's score for the quiz
}