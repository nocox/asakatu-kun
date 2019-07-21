package com.asakatu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Controller
public class Application implements WebMvcConfigurer {

    @GetMapping("/")
    public String home() {
        return "home.html";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
