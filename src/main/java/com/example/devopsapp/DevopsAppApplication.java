package com.example.devopsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller  // Changed from @RestController to @Controller for view resolution
public class DevopsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevopsAppApplication.class, args);
    }

    // This method is not needed as Spring Boot will automatically serve index.html from static/
    // But we'll keep it in case you want to add additional logic later
    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }

    @GetMapping("/health")
    @ResponseBody  // Add this to ensure the response is written directly to the response body
    public String health() {
        return "Application is healthy!";
    }
}
