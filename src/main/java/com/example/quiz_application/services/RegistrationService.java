package com.example.quiz_application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.quiz_application.config.exceptions.RegistrationException;
import com.example.quiz_application.model.Admin;
import com.example.quiz_application.model.Student;
import com.example.quiz_application.repositories.AdminRepository;
import com.example.quiz_application.repositories.StudentRepository;
import com.example.quiz_application.security.CustomUserDetails;

@Service
public class RegistrationService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(CustomUserDetails userDetails, String role) {

        if (role.equals("admin")) {

            Admin admin = Admin.builder().firstName(userDetails.getFirstName())
                    .lastName(userDetails.getLastName())
                    .username(userDetails.getUsername())
                    .password(userDetails.getPassword())
                    .build();
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminRepository.save(admin);

        } else if (role.equals("student")) {

            Student student = Student.builder().firstName(userDetails.getFirstName())
                    .lastName(userDetails.getLastName())
                    .username(userDetails.getUsername())
                    .password(userDetails.getPassword())
                    .build();
            student.setPassword(passwordEncoder.encode(student.getPassword()));
            studentRepository.save(student);

        } else {
            throw new RegistrationException("Registration Failed");
        }
    }
}
