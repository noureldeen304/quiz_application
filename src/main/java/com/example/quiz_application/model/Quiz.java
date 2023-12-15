package com.example.quiz_application.model;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "category_id", "version" }))
public class Quiz {
    @Id
    @SequenceGenerator(name = "quiz_sequence", sequenceName = "quiz_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quiz_sequence")
    Integer id;

    @Column(name = "no_of_questions")
    Integer noOfQuestions;

    String version;

    @ManyToOne
    Category category;

    @ManyToMany
    List<Question> questions;

    public List<Integer> getQuestionsIds() {
        return questions.stream().map(question -> question.getId()).collect(Collectors.toList());
    }
}
