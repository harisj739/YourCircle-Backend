package com.example.YourCircle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/test")
    public String testApiKeys() {
        apiService.useApiKeys(); // Calls the method to use the API keys 
        return "API key loaded successfully";
    }
}
