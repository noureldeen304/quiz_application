package com.example.quiz_application.services.interfaces;

import com.example.quiz_application.security.CustomUserDetails;

public interface IRegistrationService {

    void registerUser(CustomUserDetails userDetails, String role);

}
