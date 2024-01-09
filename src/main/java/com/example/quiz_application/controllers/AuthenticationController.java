package com.example.quiz_application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quiz_application.enums.Authority;
import com.example.quiz_application.security.CustomUserDetails;
import com.example.quiz_application.services.AuthenticationService;

@Controller
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new CustomUserDetails());
        return "registration";
    }

    @PostMapping("/perform_registration")
    public String processRegistrationData(@ModelAttribute("user") CustomUserDetails userDetails,
            @RequestParam String role) {

        authenticationService.registerUser(userDetails, role);
        return "redirect:successful_registration";

    }

    @GetMapping("/successful_registration")
    public String showSuccessfullRegistrationPage() {
        return "registration_success";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new CustomUserDetails());
        return "login";
    }

    @PostMapping("/perform_login")
    public void processLoginData(@ModelAttribute("user") CustomUserDetails userDetails) {
        authenticationService.authenticateUser(userDetails.getUsername(),
                userDetails.getPassword());
    }

    @GetMapping("/successful_login")
    public String showAvailableEndpointsBasedOnAuthority() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(Authority.ADMIN)) {
            return "redirect:admin_endpoints";
        }
        return "redirect:student_endpoints";
    }

    @GetMapping("/admin_endpoints")
    public String showAdminEndpoints() {
        return "endpoints_for_admin";
    }

    @GetMapping("/student_endpoints")
    public String showStudentEndpoints() {
        return "endpoints_for_student";
    }

    @GetMapping("/403")
    public String showAccessDeniedPage() {
        return "403";
    }

}