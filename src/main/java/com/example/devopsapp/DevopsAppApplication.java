package com.example.devopsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DevopsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevopsAppApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to Hello World Spring Boot Application!";
    }

    @GetMapping("/health")
    public String health() {
        return "Application is healthy!";
    }
}
