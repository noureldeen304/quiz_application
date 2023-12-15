package com.example.quiz_application.model;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @SequenceGenerator(name = "category_sequence", sequenceName = "category_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    Integer id;

    @NonNull
    @Column(name = "category_name", unique = true)
    String categoryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    List<Question> questions;

    public List<Integer> getQuestionsIds() {
        return questions.stream().map(question -> question.getId()).collect(Collectors.toList());
    }
    
}
