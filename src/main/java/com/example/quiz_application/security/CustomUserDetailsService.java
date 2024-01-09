package com.example.quiz_application.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.quiz_application.enums.Authority;
import com.example.quiz_application.model.Admin;
import com.example.quiz_application.model.Student;
import com.example.quiz_application.repositories.AdminRepository;
import com.example.quiz_application.repositories.StudentRepository;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {

            Admin admin = adminRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Admin not found: " + username));

            return new CustomUserDetails(admin.getFirstName(), admin.getLastName(), admin.getUsername(),
                    admin.getPassword(), Set.of(Authority.ADMIN));

        } catch (UsernameNotFoundException exception) {

            Student student = studentRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Student not found: " + username));

            return new CustomUserDetails(student.getFirstName(), student.getLastName(), student.getUsername(),
                    student.getPassword(), Set.of(Authority.STUDENT));

        }

    }
}
