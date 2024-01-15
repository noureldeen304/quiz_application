package com.example.quiz_application.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "category_id", "version", "admin_id" }))
@ToString
public class Quiz {
    @Id
    @SequenceGenerator(name = "quiz_sequence", sequenceName = "quiz_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quiz_sequence")
    private Integer id;

    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    @ManyToOne
    private Admin admin;

    @Column(name = "no_of_questions")
    private Integer noOfQuestions;

    private String version;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToMany
    private List<Question> questions;

    @OneToMany(mappedBy = "quiz")
    private List<QuizScore> studentScores;

    
}
