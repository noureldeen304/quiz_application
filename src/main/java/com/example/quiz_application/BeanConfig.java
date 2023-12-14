package com.example.quiz_application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    CommandLineRunner commandLineRunner() {

        return args -> {
          
        };
    }

}
