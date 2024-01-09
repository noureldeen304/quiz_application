package com.example.quiz_application.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class Authority {
    public static final SimpleGrantedAuthority ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");
    public static final SimpleGrantedAuthority STUDENT = new SimpleGrantedAuthority("ROLE_STUDENT");
}
