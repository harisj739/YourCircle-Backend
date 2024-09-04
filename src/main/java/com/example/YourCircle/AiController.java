//package com.example.YourCircle;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@RestController
//public class AiController {
//    private final AiIntegrationService aiIntegrationService;
//
//    @Autowired
//    public AiController(AiIntegrationService aiIntegrationService) {
//        this.aiIntegrationService = aiIntegrationService;
//    }
//
//    // This takes the user input and returns a recommendtion
//    @GetMapping("/recommend")
//    public String generateRecommendation(@RequestParam String input) {
//        return aiIntegrationService.generateRecommendation(input);
//    }
//}
