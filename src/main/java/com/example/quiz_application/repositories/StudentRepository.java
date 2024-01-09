package com.example.quiz_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quiz_application.model.Student;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByUsername(String username);
}
