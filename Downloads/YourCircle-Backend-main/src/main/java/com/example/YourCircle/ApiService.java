//package com.example.YourCircle;
//
//import io.github.cdimascio.dotenv.Dotenv;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ApiService {
//    private final String openAiApiKey;
//    private final String pineconeApiKey;
//
//    public ApiService() {
//        // Loading environment variables from .env file
//        Dotenv dotenv = Dotenv.load();
//        this.openAiApiKey = dotenv.get("OPENAI_API_KEY");
//        this.pineconeApiKey = dotenv.get("PINECONE_API_KEY");
//    }
//
//    public void useApiKeys() {
//        // Implementing logic using openAIApiKey and pineconeApiKey
//        System.out.println("OpenAI API Key: " + openAiApiKey);
//        System.out.println("Pinecone API Key: " + pineconeApiKey);
//    }
//}
