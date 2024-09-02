package com.example.YourCircle;

import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiIntegrationService {
    private final ChatClient chatClient;

    public AiIntegrationService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    // This method uses the OpenAI API to generate a response based on user input
    public String generateRecommendation(String input) {
        String aiResponse = chatClient.generate(input);  
        return aiResponse;
    }
}
